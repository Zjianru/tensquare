package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	/**
	 * 根据手机号查找用户
	 * @param mobile 手机号
	 * @return user
	 */
	User findByMobile(String mobile);

	/**
	 * 更新粉丝数
	 * @param x 数量
	 * @param friendid friendid
	 */
	@Modifying
	@Query(value = "UPDATE tb_user SET fanscount=fanscount+? WHERE id=?",nativeQuery = true)
	void updatefanscount(int x, String friendid);

	/**
	 * 更新关注数
	 * @param x 数量
	 * @param userid userid
	 */
	@Modifying
	@Query(value = "UPDATE tb_user SET followcount=followscount+? WHERE id=?",nativeQuery = true)
	void updatefollowcount(int x, String userid);
}
