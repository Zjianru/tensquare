package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/21
 * com.tensquare.base
 */
@SpringBootApplication
public class BaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class,args);
	}

	/**
	 * 把 ID 生成器放进 spring 容器
	 * @return IdWorker 对象
	 */
	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}

}