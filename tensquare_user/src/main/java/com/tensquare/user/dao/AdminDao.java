package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.Admin;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AdminDao extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{
	/**
	 * 登录用 —— 根据用户名查信息
	 * @param loginName 用户名
	 * @return Admin
	 */
	Admin findByLoginname(String loginName);
	
}
