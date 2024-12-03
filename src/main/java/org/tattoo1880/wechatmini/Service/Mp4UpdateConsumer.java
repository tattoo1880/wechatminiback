package org.tattoo1880.wechatmini.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.tattoo1880.wechatmini.Config.RabbitMQConfig;
import org.tattoo1880.wechatmini.Entity.Mp4;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Slf4j
public class Mp4UpdateConsumer {
	
	private final Sinks.Many<String> sink;
	
	public Mp4UpdateConsumer() {
//		this.sink = Sinks.many().multicast().onBackpressureBuffer();
		this.sink = Sinks.many().replay().limit(3);
	}
	
	@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
	public void receiveUpdate(String mp4) {
		
//		System.out.println("Received mp4 update: " + mp4);
		log.warn("Received mp4 update: {}",mp4);
		sink.tryEmitNext(mp4); // 推送消息给前端
	}
	
	public Flux<String> getMp4Updates() {
		return sink.asFlux();
	}
	
}