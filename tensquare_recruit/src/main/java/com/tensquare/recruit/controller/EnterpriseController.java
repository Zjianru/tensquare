package com.tensquare.recruit.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/25
 * com.tensquare.recruit.controller
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	/**
	 * 查询全部数据
	 * @return Result(StatusCode.OK, true,"查询成功",enterpriseService.findAll())
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(StatusCode.OK, true,"查询成功",enterpriseService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return Result(StatusCode.OK, true,"查询成功",enterpriseService.findById(id))
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(StatusCode.OK, true,"查询成功",enterpriseService.findById(id));
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
		Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
		return  new Result(StatusCode.OK, true,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap 条件 mao
     * @return Result(StatusCode.OK, true,"查询成功",enterpriseService.findSearch(searchMap))
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(StatusCode.OK, true,"查询成功",enterpriseService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param enterprise Enterprise 对象
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Enterprise enterprise  ){
		enterpriseService.add(enterprise);
		return new Result(StatusCode.OK, true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param enterprise Enterprise 对象
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Enterprise enterprise, @PathVariable String id ){
		enterprise.setId(id);
		enterpriseService.update(enterprise);		
		return new Result(StatusCode.OK, true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id 参数 --> id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		enterpriseService.deleteById(id);
		return new Result(StatusCode.OK, true,"删除成功");
	}

	/**
	 * 查询热门列表
	 * @return Result(StatusCode.OK,true,"查询成功",enterpriseService.hotList(ishot))
	 */
	@RequestMapping(value="/search/hotlist",method= RequestMethod.GET)
	public Result hotList(){
		String ishot = "1";
		return new Result(StatusCode.OK,true,"查询成功",enterpriseService.hotList(ishot));
	}
}
