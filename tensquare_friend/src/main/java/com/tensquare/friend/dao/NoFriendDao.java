package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/5
 * com.tensquare.friend.dao
 */
public interface FriendDao extends JpaRepository<Friend,String> {
	/**
	 * 根据userid和friendid查找
	 * @param userid userid
	 * @param friendid friendid
	 * @return Friend
	 */
	Friend findByUseridAndFriendid(String userid, String friendid);

	@Modifying
	@Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?",nativeQuery = true)
	void updateIslike(String islike,String userid, String friendid);
}
