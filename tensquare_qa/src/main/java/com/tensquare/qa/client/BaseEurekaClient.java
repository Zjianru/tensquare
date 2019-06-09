package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.BaseEurekaClientImpl;
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
@FeignClient(value = "tensquare-base",fallback = BaseEurekaClientImpl.class)
public interface BaseEurekaClient {
	/**
	 * 调用测试
	 * @param labelId labelid
	 * @return Result
	 */
	@RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
	Result findById(@PathVariable("labelId") String labelId) ;
}
