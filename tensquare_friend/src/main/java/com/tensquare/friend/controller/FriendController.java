package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.common.FriendState;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import entity.TokenCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownServiceException;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/5
 * com.tensquare.friend.controller
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserClient userClient;

	/**
	 * 添加好友或非好友 —— 非双向关注
	 * @param friendid friendid
	 * @param type type
	 * @return Result
	 */
	@RequestMapping(value = "/like/{friendid}/{type}",method= RequestMethod.PUT)
	public Result add(@PathVariable String friendid,
	                  @PathVariable String type){

		Claims claims = (Claims) httpServletRequest.getAttribute(TokenCode.CLAIMS_ROLE_USER);
		if (claims == null ){
			return new Result(StatusCode.ACCESSERROR, false,"权限不足");
		}

		String userid = claims.getId();
		if (type != null){
			if (FriendState.LIKE_YOU.equals(type)){
				// 添加好友
				int flag = friendService.addFriend(userid,friendid);
				if (flag == 0){
					return new Result(StatusCode.ERROR, false,"你们已经是好友了");
				}
				if (flag == 1){
					userClient.updateFanscountAndFollowcount(userid,friendid,1);
					return new Result(StatusCode.OK, true,"添加成功");
				}
			}else if (FriendState.NOT_LIKE_YOU.equals(type)){
				//添加非好友
				int flag =  friendService.addNoFriend(userid, friendid);
				if(flag==0){
					return new Result(StatusCode.ERROR, false, "不能重复添加非好友");
				}
				if(flag==1){
					return new Result(StatusCode.OK, true, "添加成功");
				}
			}
			return new Result(StatusCode.ERROR, false, "参数异常");
		}else {
			return new Result(StatusCode.ERROR, false, "参数异常");
		}
	}

	/**
	 * 删除好友
	 * @param friendid friendid
	 * @return Result
	 */
	@RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
	public Result deleteFriend(@PathVariable String friendid){
		//验证是否登录，并且拿到当前登录的用户id
		Claims claims = (Claims) httpServletRequest.getAttribute("claims_user");
		if(claims==null){
			//说明当前用户没有user角色
			return new Result(StatusCode.LOGINERROR, false, "权限不足");
		}
		//得到当前登录的用户id
		String userid = claims.getId();
		friendService.deleteFriend(userid, friendid);
		userClient.updateFanscountAndFollowcount(userid, friendid, -1);
		return new Result(StatusCode.OK, true, "删除成功");
	}



}