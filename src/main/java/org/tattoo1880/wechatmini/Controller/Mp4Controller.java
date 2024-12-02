package org.tattoo1880.wechatmini.Controller;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.tattoo1880.wechatmini.Entity.Mp4;
import org.tattoo1880.wechatmini.Service.Mp4Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/mp4")
@CrossOrigin(origins = "http://193.32.150.11", allowCredentials = "true")
public class Mp4Controller {
	@Data
	public class Mp4to {
		private String id;
		private String urls;
		private String title;
		
		private LocalDateTime createTime = LocalDateTime.now();
		private Long updateTime = System.currentTimeMillis();
	}

	@Autowired
	private Mp4Service mp4Service;
	

//	========
	
	@PostMapping("/findOne")
	public Mono<Mp4> findAll(@RequestBody Map obj) {
		
		String tartgetUrl = (String) obj.get("url");
		return mp4Service.findAll(tartgetUrl);
	}
	
	@GetMapping("/findAll")
	public Flux<Mp4to> realfindAll() {
		return mp4Service.RealFindAll()
				.flatMap(mp4 -> {
					Mp4to mp4to = new Mp4to();
					mp4to.setId(mp4.getId().toString());
					mp4to.setUrls(mp4.getUrls());
					mp4to.setTitle(mp4.getTitle());
					mp4to.setCreateTime(mp4.getCreateTime());
					mp4to.setUpdateTime(mp4.getUpdateTime());
					return Mono.just(mp4to);
				});
	}
	
	@GetMapping("/deleteAll")
	public Mono<Void> deleteAll() {
		return mp4Service.deleteAll();
	}
	
	@PostMapping("/deleteById")
	public Mono<?> deleteById(@RequestBody Map obj) {
		try {
			Object id = obj.get("id");
			System.out.println(id);
			return mp4Service.delteById(Long.parseLong(id.toString()));
		}
		catch (Exception e) {
			System.out.println(e);
			return Mono.just("error");
		}
		
	}
	
	@PostMapping("/updateTitle")
	public Mono<Mp4> updateTitle(@RequestBody Map obj) {
		try {
			Object id = obj.get("id");
			Object title = obj.get("title");
			return mp4Service.updateTitle(Long.parseLong(id.toString()), title.toString());
		}
		catch (Exception e) {
			System.out.println(e);
			return Mono.just(new Mp4());
		}
	}
	
}
