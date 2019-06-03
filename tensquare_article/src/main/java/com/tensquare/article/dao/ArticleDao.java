package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/27
 * com.tensquare.article.dao
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
	/**
	 * 修改审核状态为  已审核
	 * @param id id
	 */
	@Modifying
	@Query(nativeQuery = true,value = "update tb_article SET state=1 where id = ?")
	void updateState(String id);

	/**
	 * 点赞
	 * @param id id
	 */
	@Modifying
	@Query(nativeQuery = true,value = "update tb_article SET thumbup=thumbup+1 where id = ?")
	void addThumbup(String id);
}
