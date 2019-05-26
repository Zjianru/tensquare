package com.tensquare.qa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/26
 * com.tensquare.qa.controller
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;


	/**
	 * 查询全部数据
	 *
	 * @return Result(StatusCode.OK, true, " 查询成功 ", problemService.findAll ())
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(StatusCode.OK, true, "查询成功", problemService.findAll());
	}

	/**
	 * 根据ID查询
	 *
	 * @param id ID
	 * @return Result(StatusCode.OK, true, " 查询成功 ", problemService.findById ( id))
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result findById(@PathVariable String id) {
		return new Result(StatusCode.OK, true, "查询成功", problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 *
	 * @param searchMap 查询条件封装
	 * @param page      页码
	 * @param size      页大小
	 * @return 分页结果
	 */
	@RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return new Result(StatusCode.OK, true, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
	}

	/**
	 * 根据条件查询
	 *
	 * @param searchMap 参数 map
	 * @return Result(StatusCode.OK, true, " 查询成功 ", problemService.findSearch ( searchMap))
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap) {
		return new Result(StatusCode.OK, true, "查询成功", problemService.findSearch(searchMap));
	}

	/**
	 * 增加
	 *
	 * @param problem 封装对象
	 * @return Result(StatusCode.OK, true, " 增加成功 ")
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@RequestBody Problem problem) {
		problemService.add(problem);
		return new Result(StatusCode.OK, true, "增加成功");
	}

	/**
	 * 修改
	 *
	 * @param problem 封装对象
	 * @param id      id
	 * @return Result(StatusCode.OK, true, " 修改成功 ")
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id) {
		problem.setId(id);
		problemService.update(problem);
		return new Result(StatusCode.OK, true, "修改成功");
	}

	/**
	 * 删除
	 *
	 * @param id id
	 * @return Result(StatusCode.OK, true, " 删除成功 ")
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		problemService.deleteById(id);
		return new Result(StatusCode.OK, true, "删除成功");
	}

	/**
	 * @param label 标签 id
	 * @param page 页码
	 * @param size 页容量
	 * @return Result
	 */
	@RequestMapping(value = "/newlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public Result newList(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
		Page<Problem> problems = problemService.newList(label, page, size);
		return new Result(StatusCode.OK, true, "", new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
	}


	/**
	 * @param label 标签 id
	 * @param page 页码
	 * @param size 页容量
	 * @return Result
	 */
	@RequestMapping(value = "/hotlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public Result hotList(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
		Page<Problem> problems = problemService.hotList(label, page, size);
		return new Result(StatusCode.OK, true, "", new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
	}


	/**
	 * @param label 标签 id
	 * @param page 页码
	 * @param size 页容量
	 * @return Result
	 */
	@RequestMapping(value = "/waitlist/{label}/{page}/{size}", method = RequestMethod.GET)
	public Result waitList(@PathVariable String label, @PathVariable int page, @PathVariable int size) {
		Page<Problem> problems = problemService.waitList(label, page, size);
		return new Result(StatusCode.OK, true, "", new PageResult<Problem>(problems.getTotalElements(), problems.getContent()));
	}












}