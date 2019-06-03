package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/29
 * com.tensquare.search
 */
@SpringBootApplication

public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker();
	}
}
