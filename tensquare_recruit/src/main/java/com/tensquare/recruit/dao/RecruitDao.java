package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/25
 * com.tensquare.recruit.dao
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	/**
	 * 查找推荐职位 按照时间排序
	 * @param state 职位状态
	 * @return List<Recruit>
	 */
	List<Recruit> findAllByStateOrderByCreatetime(String state);

	/**
	 * 查找最新职位
	 * @param state 职位状态
	 * @return List<Recruit>
	 */
	List<Recruit> findByStateNotOrderByCreatetimeDesc(String state);
}
