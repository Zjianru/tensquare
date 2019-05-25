package com.tensquare.recruit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/5/25
 * com.tensquare.recruit
 */
@SpringBootApplication
public class RecruitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruitApplication.class, args);
	}

	@Bean
	public IdWorker idWorker(){
		return new IdWorker(1, 1);
	}
	
}
