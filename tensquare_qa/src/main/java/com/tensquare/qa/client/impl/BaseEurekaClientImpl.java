package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseEurekaClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/5
 * com.tensquare.qa.client.impl
 */
@Component
public class BaseEurekaClientImpl implements BaseEurekaClient {
	@Override
	public Result findById(String labelId) {
		return new Result(StatusCode.ERROR,false,"熔断器触发");
	}
}
