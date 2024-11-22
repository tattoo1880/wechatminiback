package org.tattoo1880.wechatmini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 启用定时任务

public class WechatminiApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WechatminiApplication.class, args);
	}
	
}
