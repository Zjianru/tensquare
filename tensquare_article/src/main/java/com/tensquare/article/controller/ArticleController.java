package com.tensquare.article.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/26
 * com.tensquare.article.controller
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	
	/**
	 * 查询全部数据
	 * @return  Result(StatusCode.OK, true,"查询成功",articleService.findAll())
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(StatusCode.OK, true,"查询成功",articleService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return Result(StatusCode.OK, true,"查询成功",articleService.findById(id))
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(StatusCode.OK, true,"查询成功",articleService.findById(id));
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
		Page<Article> pageList = articleService.findSearch(searchMap, page, size);
		return  new Result(StatusCode.OK, true,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap 条件
     * @return Result(StatusCode.OK, true,"查询成功",articleService.findSearch(searchMap))
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(StatusCode.OK, true,"查询成功",articleService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param article Article
	 * @return  Result(StatusCode.OK, true,"增加成功")
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Article article  ){
		articleService.add(article);
		return new Result(StatusCode.OK, true,"增加成功");
	}
	
	/**
	 * 修改
	 * @param article Article
	 * @param id id
	 * @return Result(StatusCode.OK, true,"修改成功")
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Article article, @PathVariable String id ){
		article.setId(id);
		articleService.update(article);

		return new Result(StatusCode.OK, true,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		articleService.deleteById(id);
		return new Result(StatusCode.OK, true,"删除成功");
	}

	/**
	 * 修改审核状态为  已审核
	 * @param articleId id
	 * @return Result(StatusCode.OK, true,"审核成功")
	 */
	@RequestMapping(value="examine/{articleId}",method= RequestMethod.PUT)
	public Result updateState(@PathVariable String articleId ){
		articleService.updateState(articleId);
		return new Result(StatusCode.OK, true,"审核成功");
	}
	/**
	 * 点赞
	 * @param articleId id
	 * @return Result(StatusCode.OK, true,"点赞成功")
	 */
	@RequestMapping(value="/thumbup/{articleId}",method= RequestMethod.PUT)
	public Result addThumbup(@PathVariable String articleId ){
		articleService.addThumbup(articleId);
		return new Result(StatusCode.OK, true,"点赞成功");
	}


}
