package org.tattoo1880.wechatmini.Controller;


import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/token")
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class TokenController {
	
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	
	
	@PostMapping("save")
	public Mono<String> saveToken(@RequestBody Map obj) {
		String token = (String) obj.get("token");
		String openId = (String) obj.get("openId");
		redisTemplate.opsForValue().set(openId, token, Duration.ofSeconds(60));
		return Mono.just("success");
	}
	
	@PostMapping("get")
	public Mono<String> getToken(@RequestBody Map obj) {
		String openId = (String) obj.get("openId");
		return Mono.create(sink -> {
			String token = redisTemplate.opsForValue().get(openId);
			sink.success(Objects.requireNonNullElse(token, "no token"));
		});
	}
}
