package org.tattoo1880.wechatmini.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattoo1880.wechatmini.Service.Mp4UpdateConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sse")
//@CrossOrigin
@Slf4j
public class SseController {
	
	
	
	
	private final Mp4UpdateConsumer mp4UpdateConsumer;
	
	public SseController(Mp4UpdateConsumer mp4UpdateConsumer) {
		this.mp4UpdateConsumer = mp4UpdateConsumer;
	}


//	========
	
	@GetMapping(value = "/updates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamMp4Updates() {
		Flux<String> clientDisconnected = mp4UpdateConsumer.getMp4Updates()
				.doOnCancel(() -> System.out.println("Client disconnected"))
				.doOnComplete(() -> System.out.println("Client completed"))
				.doOnError(e -> System.out.println("Client error"))
				.doFinally(signalType -> System.out.println("Client finally: " + signalType));
		
		//! 心跳
		Flux<String> heartbeat = Flux.interval(java.time.Duration.ofSeconds(2))
				.map(i -> "heartbeat")
				.doOnEach(signal -> System.out.println("Heartbeat: " + signal.get()));
		
		
		return Flux.mergeDelayError(1, clientDisconnected, heartbeat);
	}

}
