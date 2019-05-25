package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.IdWorker;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * com.tensquare.base.controller
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
	@Autowired
	private LabelService labelService;

	/**
	 * 增加标签
	 *
	 * @param label Label 标签对象
	 * @return Result(status, flag, message)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result save(@RequestBody Label label) {
		labelService.save(label);
		return new Result(StatusCode.OK, true, "保存成功");
	}

	/**
	 * 标签全部列表
	 *
	 * @return Result(status, flag, message, data)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result findAll() {
		return new Result(StatusCode.OK, true, "标签全部列表", labelService.findAll());
	}


	/**
	 * 推荐列表
	 *
	 * @return Result
	 */
	@RequestMapping(value = "toplist", method = RequestMethod.GET)
	public Result topList() {
		//todo 推荐列表
		return new Result(StatusCode.OK, true, "推荐列表");
	}

	/**
	 * 有效标签列表
	 *
	 * @return Result
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result list() {
		//todo 有效标签列表
		return new Result(StatusCode.OK, true, "有效标签列表");
	}

	/**
	 * 根据 ID 查询标签
	 *
	 * @param labelId 标签ID
	 * @return Result(status, flag, message, code)
	 */
	@RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
	public Result findById(@PathVariable("labelId") String labelId) {

		return new Result(StatusCode.OK, true, "查询成功", labelService.findById(labelId));
	}


	/**
	 * 更新标签
	 *
	 * @param labelId 标签ID
	 * @param label   标签信息对象
	 * @return Result(status, flag, message)
	 */

	@RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
	public Result updateById(@PathVariable("labelId") String labelId,
	                         @RequestBody Label label) {
		label.setId(labelId);
		labelService.updateById(label);
		return new Result(StatusCode.OK, true, "更新成功");
	}


	/**
	 * 根据 ID 删除标签
	 *
	 * @param labelId 标签ID
	 * @return Result(status, flag, message)
	 */
	@RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
	public Result deleteById(@PathVariable("labelId") String labelId) {
		labelService.deleteById(labelId);
		return new Result(StatusCode.OK, true, "删除成功");
	}

	/**
	 * 标签分页
	 *
	 * @param label 标签信息
	 * @return Result(status, flag, message, code)
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Result findSearch(@RequestBody Label label) {
		List<Label> labels = labelService.findSearch(label);
		return new Result(StatusCode.OK, true, "查询成功", labels);
	}

	/**
	 * 标签分页
	 * @param label 标签信息
	 * @param page 分页
	 * @param size 页内数量
	 * @return Result(status,flag,message,data)
	 */
	@RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
	public Result pageQuery(@RequestBody Label label,
	                        @PathVariable("page") Integer page,
	                        @PathVariable("size") Integer size) {

		List<Label> labels = labelService.pageQuery(label,page,size);



		return new Result(StatusCode.OK, true, "查询成功", labels);
	}









}