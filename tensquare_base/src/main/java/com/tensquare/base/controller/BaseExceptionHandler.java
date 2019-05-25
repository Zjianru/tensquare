package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * com.tensquare.base.controller
 */
@RestControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public Result error(Exception e) {
		e.printStackTrace();
		return new Result(StatusCode.ERROR, false, e.getMessage());
	}
}
