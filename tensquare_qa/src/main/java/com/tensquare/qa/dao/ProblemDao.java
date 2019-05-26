package com.tensquare.qa.dao;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/26
 * com.tensquare.qa.dao
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

	/**
	 * 最新回答
	 *
	 * @param labelid  标签id
	 * @param pageable 开启分页
	 * @return Page<Problem>
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid = ? ORDER BY replytime DESC")
	Page<Problem> newList(String labelid, Pageable pageable);

	/**
	 * 最热回答
	 *
	 * @param labelid  标签id
	 * @param pageable 开启分页
	 * @return Page<Problem>
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid = ? ORDER BY reply DESC")
	Page<Problem> hotList(String labelid, Pageable pageable);

	/**
	 * 等待回答 —— 零回答惨案
	 *
	 * @param labelid  标签id
	 * @param pageable 开启分页
	 * @return Page<Problem>
	 */
	@Query(nativeQuery = true,
			value = "SELECT * FROM tb_problem,tb_pl WHERE id=problemid AND labelid = ? AND reply>5 ORDER BY createtime DESC")
	Page<Problem> waitList(String labelid, Pageable pageable);
}
