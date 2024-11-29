package org.tattoo1880.wechatmini.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tattoo1880.wechatmini.Config.RabbitMQConfig;
import org.tattoo1880.wechatmini.Entity.Mp4;
import org.tattoo1880.wechatmini.Repositories.Mp4Repository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Mp4UpdateProducer {
	
	private final RabbitTemplate rabbitTemplate;
	private final Mp4Repository mp4Repository;
	private final ObjectMapper jacksonObjectMapper;
	
	public Mp4UpdateProducer(RabbitTemplate rabbitTemplate, Mp4Repository mp4Repository, ObjectMapper jacksonObjectMapper) {
		this.rabbitTemplate = rabbitTemplate;
		this.mp4Repository = mp4Repository;
		this.jacksonObjectMapper = jacksonObjectMapper;
	}
	
	@Scheduled(fixedRate = 5000) // 每5秒检查一次
	public void checkForNewMp4Entries() throws JsonProcessingException {
		List<Mp4> newEntries = mp4Repository.findNewEntries(LocalDateTime.now().minusSeconds(5)).collectList().block();
		if (!newEntries.isEmpty()) {
			System.out.println("有新货物了!!!!!!!!");
			for (Mp4 mp4 : newEntries) {
				//! 将mp4对象变成json字符串发送
				String s = jacksonObjectMapper.writeValueAsString(mp4);
				rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, s);
			}
		}else {
			//! 发送一个空对象，以便前端知道没有新的数据
//			System.out.println("没有新货物了!!!!!!!!");
			Mp4 mp4 = new Mp4();
			mp4.setUrls("...");
			mp4.setTitle("...");
			mp4.setId(0L);
//			String mp4json = mp4.toString();
			String s = jacksonObjectMapper.writeValueAsString(mp4);
			
			rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, s);
		}
		
		// 为了测试，每次都发送一个空对象
//		rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, "test");
		}
	
}
