package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/29
 * com.tensquare.search.service
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;


	/**
	 * 分词查询  查询区域包括文章内容和标题
	 * @param key 关键字
	 * @param page 分页
	 * @param size 页内容量
	 * @return Page
	 */
	public Page findByKey(String key, int page, int size) {
		return articleDao.findByTitleOrContentLike(key,key,PageRequest.of(page - 1, size));
	}
}