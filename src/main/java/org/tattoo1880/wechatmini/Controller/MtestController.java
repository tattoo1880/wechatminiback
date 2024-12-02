package org.tattoo1880.wechatmini.Controller;


import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tattoo1880.wechatmini.Entity.Mtest;
import org.tattoo1880.wechatmini.Service.MtestService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/mtest")
//@CrossOrigin
public class MtestController {
	
	
	@Autowired
	private MtestService mtestService;
	
	@PostMapping("/savetest")
	public Mono<Mtest> save(@RequestBody Mtest mtest) {
		return mtestService.save(mtest);
	}
	
	@GetMapping("/findall")
	public Flux<Mtest> findAll() {
		return mtestService.findAll();
	}
	
	@DeleteMapping("/deleteall")
	
	
//	ok
	public Mono<ResponseEntity<String>> deleteall() {
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");
		return mtestService.deleteall().thenReturn(ResponseEntity.ok(jsonObject.toString()))
				.onErrorReturn(ResponseEntity.badRequest().body("delete all failed"));
		
		
	}
}
