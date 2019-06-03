package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/28
 * com.tensquare.spit.controller
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

	@Autowired
	private SpitService spitService;
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 增加吐槽
	 *
	 * @param spit spit
	 * @return Result(StatusCode.OK, true, " 保存成功 ")
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Spit spit) {
		spitService.save(spit);
		return new Result(StatusCode.OK, true, "保存成功");
	}

	/**
	 * Spit全部列表
	 *
	 * @return Result(StatusCode.OK, true, " 查询成功 ", spitService.findAll ())
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(StatusCode.OK, true, "查询成功", spitService.findAll());
	}

	/**
	 * 根据ID查询吐槽
	 *
	 * @param spitId id
	 * @return Result(StatusCode.OK, true, " 查询成功 ", spitService.findById ( spitId))
	 */
	@RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
	public Result findById(@PathVariable String spitId) {
		return new Result(StatusCode.OK, true, "查询成功", spitService.findById(spitId));
	}


	/**
	 * 修改吐槽
	 *
	 * @param spit   spit 对象
	 * @param spitId id
	 * @return Result(StatusCode.OK, true, " 更新成功 ")
	 */
	@RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
	public Result update(@RequestBody Spit spit,
	                     @PathVariable String spitId) {
		spit.set_id(spitId);
		spitService.update(spit);
		return new Result(StatusCode.OK, true, "更新成功");
	}


	/**
	 * 根据ID删除吐槽
	 *
	 * @param spitId id
	 * @return Result(StatusCode.OK, true, " 删除成功 ")
	 */
	@RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String spitId) {
		spitService.deleteById(spitId);
		return new Result(StatusCode.OK, true, "删除成功");
	}

	/**
	 * 根据上级 id 查询
	 *
	 * @param parentid 上级 id
	 * @param page     分页
	 * @param size     页内容量
	 * @return Result(StatusCode.OK, true, " 根据上级 id 查询成功 ", new PageResult < Spit > ( pageData.getTotalElements (), pageData.getContent()))
	 */
	@RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
	public Result findByParentid(@PathVariable String parentid,
	                             @PathVariable int page,
	                             @PathVariable int size) {
		Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
		return new Result(StatusCode.OK, true, "根据上级 id 查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
	}

	/**
	 * 点赞
	 * @param spitId id
	 * @return Result(StatusCode.OK, true, "点赞成功")
	 */
	@RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
	public Result thumbup(@PathVariable String spitId) {
		//todo 没做认证  暂时写死 userid 后期补充完整
		String userid = "5cecd801e3662c08fc0031c3";
		if (redisTemplate.opsForValue().get("thumbup_"+userid) != null){
			return new Result(StatusCode.REPERROR, false, "点赞虽好，可不要贪多呦");
		}
		spitService.thumbup(spitId);
		redisTemplate.opsForValue().set("thumbup_"+userid,"thumbed");
		return new Result(StatusCode.OK, true, "点赞成功");
	}

}