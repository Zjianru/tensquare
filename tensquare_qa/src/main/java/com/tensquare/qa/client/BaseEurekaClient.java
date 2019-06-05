package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/4
 * com.tensquare.qa.client
 */
@FeignClient("tensquare-base")
public interface BaseEurekaClient {
	/**
	 * 调用测试
	 * @param labelId
	 * @return
	 */
	@RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
	Result findById(@PathVariable("labelId") String labelId) ;
}
