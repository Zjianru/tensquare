package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/5
 * com.tensquare.friend.client
 */
@FeignClient("tensquare-user")
public interface UserClient {
	/**
	 * 更新粉丝数和关注数
	 * @param userid userid
	 * @param friendid friendid
	 * @param x 数量
	 */
	@RequestMapping(value = "/user/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
	void updateFanscountAndFollowcount(@PathVariable("userid") String userid,
	                                          @PathVariable("friendid") String friendid,
	                                          @PathVariable("x") int x);
}
