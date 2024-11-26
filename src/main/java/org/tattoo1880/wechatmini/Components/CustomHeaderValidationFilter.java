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
		
		// 排除 /sse 路径的请求
		if (path.startsWith("/sse")) {
			// 如果是 /sse 路径，直接跳过验证，继续处理请求
			return chain.filter(exchange);
		}
		if(path.contains("login")){
			return chain.filter(exchange);
		}
		// 获取请求头
		HttpHeaders headers = exchange.getRequest().getHeaders();
		String customHeader = headers.getFirst("sec_token");
		
		// 验证请求头是否存在且值可以通过jwt的验证
		if (customHeader == null || !jwtUtils.verifyToken(customHeader)) {
			// 如果验证失败，返回401状态码
			exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
			return exchange.getResponse().setComplete();
		}
		
		
		System.out.println("=======???**JWT**??=======");
		System.out.println(jwtUtils.parseToken(customHeader));
		// 如果验证成功，继续处理请求
		return chain.filter(exchange);
	}
}
