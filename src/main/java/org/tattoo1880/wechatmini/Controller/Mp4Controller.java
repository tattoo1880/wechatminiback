package org.tattoo1880.wechatmini.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tattoo1880.wechatmini.Entity.Mp4;
import org.tattoo1880.wechatmini.Service.Mp4Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/mp4")
public class Mp4Controller {

	@Autowired
	private Mp4Service mp4Service;
	
	@PostMapping("/findOne")
	public Mono<Mp4> findAll(@RequestBody Map obj) {
		
		String tartgetUrl = (String) obj.get("url");
		return mp4Service.findAll(tartgetUrl);
	}
	
	@GetMapping("/findAll")
	public Flux<Mp4> realfindAll() {
		return mp4Service.RealFindAll();
	}
	
	@GetMapping("/deleteAll")
	public Mono<Void> deleteAll() {
		return mp4Service.deleteAll();
	}
	
	
}
