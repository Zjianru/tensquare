package com.tensquare.friend.service;

import com.netflix.discovery.converters.Auto;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.common.FriendState;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/5
 * com.tensquare.friend.service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FriendService {

	@Autowired
	private FriendDao friendDao;
	@Autowired
	private NoFriendDao noFriendDao;

	/**
	 * 添加friend
	 * @param userid userid
	 * @param friendid friendid
	 * @return 1 success / 0 unsuccessful
	 */
	public int addFriend(String userid, String friendid) {
		friendDao.findByUseridAndFriendid(userid,friendid);
		if (friendid != null){
			return FriendState.ALREADY_FRIEND;
		}
		Friend friend = new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIslike("0");
		friendDao.save(friend);
		if (friendDao.findByUseridAndFriendid(friendid,userid) != null){
			friendDao.updateIslike(FriendState.IS_LIKE,userid,friendid);
			friendDao.updateIslike(FriendState.IS_LIKE,friendid,userid);
		}
		return FriendState.ADD_FRIEND_SUCCESS;
	}

	/**
	 * 添加黑名单
	 * @param userid userid
	 * @param friendid friendid
	 * @return 0/1
	 */
	public int addNoFriend(String userid, String friendid) {
		NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
		if (noFriend != null){
			return 0;
		}
		NoFriend result = new NoFriend();
		result.setUserid(userid);
		result.setFriendid(friendid);
		noFriendDao.save(result);
		return 1;
	}

	/**
	 * 删除好友
	 * @param userid userid
	 * @param friendid friendid
	 */
	public void deleteFriend(String userid, String friendid) {
		friendDao.deleteFriend(userid,friendid);
		friendDao.updateIslike("0",friendid,userid);
		NoFriend noFriend = new NoFriend();
		noFriend.setUserid(userid);
		noFriend.setFriendid(friendid);
		noFriendDao.save(noFriend);
	}
}
