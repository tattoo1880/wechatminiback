package org.tattoo1880.wechatmini.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tattoo1880.wechatmini.Entity.User;
import org.tattoo1880.wechatmini.Service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/saveorupdate")
	public Mono<User> saveorupdate(@RequestBody User user) {
//		System.out.println(obj);
//
//		User user = new User();
//		user.setId((String) obj.get("id"));
//		user.setUsername((String) obj.get("username"));
//		user.setPassword((String) obj.get("password"));
//		user.setEmail((String) obj.get("email"));
//		user.setPhone((String) obj.get("phone"));
		
		
		System.out.println(user);
		return userService.saveorupdate(user);
//		return Mono.empty();
	}
	
	
	@GetMapping("/findAll")
	public Flux<User> findAll() {
		System.out.println("??????????");
		return userService.findAll();
	}
	
	
	@PostMapping("/login")
	public Mono<User> login(@RequestBody Map<String, String> map) {
		String username = map.get("username");
		String password = map.get("password");
		return userService.login(username, password);
	}
}
