package org.tattoo1880.wechatmini.Components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CustomHeaderValidationFilter implements WebFilter {
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		
		
		String path = exchange.getRequest().getPath().value();
		
		
		System.out.println("=======???=======???=======");
		System.out.println(path);
		System.out.println("=======???=======???=======");
		
		
		//todo 需要验证的路径 1.包含 /mp4/ 与 /mtest/ 与 /token  2.与/user/findAll相等,校验,其他不校验
		
		
//		// 排除 /sse 路径的请求
//		if (path.startsWith("/sse")) {
//			// 如果是 /sse 路径，直接跳过验证，继续处理请求
//			return chain.filter(exchange);
//		}
//		if(path.contains("login")){
//			return chain.filter(exchange);
//		}
//		if(path.contains("/user/saveorupdate")){
//			return chain.filter(exchange);
//		}
//
//		//!不验证根路由
//		if(path.equals("/")){
//			System.out.println("根路由");
//			return chain.filter(exchange);
//		}
//		//!不验证assets
//		if(path.contains("assets")){
//			return chain.filter(exchange);
//		}
//		if( path.contains("mtest") || path.contains("jpg") || path.contains("png") || path.contains("jpeg") || path.contains("gif") || path.contains("js") || path.contains("css") || path.contains("ico") || path.contains("svg")){
//			return chain.filter(exchange);
//		}
//		// 获取请求头
//		HttpHeaders headers = exchange.getRequest().getHeaders();
//		String customHeader = headers.getFirst("sec_token");
//
//		// 验证请求头是否存在且值可以通过jwt的验证
//		if (customHeader == null || !jwtUtils.verifyToken(customHeader)) {
//			// 如果验证失败，返回401状态码
//			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//			return exchange.getResponse().setComplete();
//		}
//
//
//		System.out.println("=======???**JWT**??=======");
//		System.out.println(jwtUtils.parseToken(customHeader));
//		// 如果验证成功，继续处理请求
//		return chain.filter(exchange);
		
		
		//todo 需要验证的路径 1.包含 /mp4/ 与 /mtest/ 与 /token  2.与/user/findAll相等,校验,其他不校验
		if(path.contains("/mp4/") || path.contains("/mtest/") || path.contains("/token") || path.equals("/user/findAll")){
			// 获取请求头
			HttpHeaders headers = exchange.getRequest().getHeaders();
			String customHeader = headers.getFirst("sec_token");
			
			// 验证请求头是否存在且值可以通过jwt的验证
			if (customHeader == null || !jwtUtils.verifyToken(customHeader)) {
				// 如果验证失败，返回401状态码
				exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
				return exchange.getResponse().setComplete();
			}
			
			// 如果验证成功，继续处理请求
			return chain.filter(exchange);
		}
		else{
			// 如果不需要验证，直接继续处理请求
			return chain.filter(exchange);
		}
	}
}
