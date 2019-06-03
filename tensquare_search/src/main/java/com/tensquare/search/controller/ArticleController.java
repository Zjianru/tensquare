package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/29
 * com.tensquare.search.controller
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	/**
	 * 分词查询  查询区域包括文章内容和标题
	 * @param key 关键字
	 * @param page 分页
	 * @param size 页内容量
	 * @return Result
	 */
	@RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
	public Result findByKey(@PathVariable String key,
	                        @PathVariable int page,
	                        @PathVariable int size) {
		Page pageData =  articleService.findByKey(key,page,size);
		return new Result(StatusCode.OK, true, "查询成功", new PageResult<Article>(pageData.getTotalElements(),pageData.getContent()));
	}

}