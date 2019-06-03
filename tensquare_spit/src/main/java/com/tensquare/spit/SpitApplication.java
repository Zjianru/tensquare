package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/28
 * com.tensquare.spit
 */
@SpringBootApplication
public class SpitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpitApplication.class,args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}
}