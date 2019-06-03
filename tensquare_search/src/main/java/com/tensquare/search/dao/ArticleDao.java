package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/29
 * com.tensquare.search.dao
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {
	/**
	 * 分词查询  查询区域包括文章内容和标题
	 * @param title 标题
	 * @param content 文章内容
	 * @param pageable 开启分页
	 * @return Page<Article>
	 */
	Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}