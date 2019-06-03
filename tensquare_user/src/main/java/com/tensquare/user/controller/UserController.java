package com.tensquare.user.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(StatusCode.OK, true,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(StatusCode.OK, true,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(StatusCode.OK, true,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(StatusCode.OK, true,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(StatusCode.OK, true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(StatusCode.OK, true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(StatusCode.OK, true,"删除成功");
	}


	/**
	 * 发送验证码
	 * @param mobile 手机号
	 * @return Result
	 */
	@RequestMapping(value="/sendsms/{mobile}",method= RequestMethod.POST)
	public Result sendSMS(@PathVariable String mobile ){
		userService.sendSms(mobile);
		return new Result(StatusCode.OK, true,"验证码发送成功");
	}


	/**
	 * 用户注册
	 * @param code 验证码
	 * @param user user info
	 * @return Result
	 */
	@RequestMapping(value="/register/{code}",method= RequestMethod.POST)
	public Result register(@PathVariable String code,
	                       @RequestBody User user){
		String checkcodeRedis = (String) redisTemplate.opsForValue().get("_checkcode"+user.getMobile());
		System.out.println("checkcodeRedis =-=-=-=-=-=-=-=-= "+checkcodeRedis);
		if (checkcodeRedis != null && checkcodeRedis.isEmpty()){
			return new Result(StatusCode.ERROR,false,"请先获取验证码!");
		}
		if (!code.equals(checkcodeRedis)){
			return new Result(StatusCode.ERROR,false,"验证码错误!");
		}
		userService.add(user);
		return new Result(StatusCode.OK, true,"注册成功");
	}

	/**
	 * 登录
	 * @param user user info
	 * @return Result
	 */
	@RequestMapping(value = "/login",method= RequestMethod.POST)
	public Result login(@RequestBody User user ){
		if (userService.login(user) == null){
			return new Result(StatusCode.LOGINERROR,false,"登陆失败");
		}
		//todo 调整用户信息，写入session —— JWT
		return new Result(StatusCode.OK, true,"登陆成功");
	}



}
