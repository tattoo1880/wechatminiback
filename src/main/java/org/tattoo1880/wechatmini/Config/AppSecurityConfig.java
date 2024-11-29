package org.tattoo1880.wechatmini.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.tattoo1880.wechatmini.Components.AppReactiveUserDetailService;
import org.tattoo1880.wechatmini.Components.CustomHeaderValidationFilter;

import java.nio.file.PathMatcher;


@Configuration
@EnableWebFluxSecurity

public class AppSecurityConfig {
	
	@Autowired
	AppReactiveUserDetailService appReactiveUserDetailService;
	
	@Autowired
	CustomHeaderValidationFilter customHeaderFilter;
	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange(
				authorizeExchangeSpec -> {
					authorizeExchangeSpec
							//! 静态static 全放开
							.pathMatchers("/js/**", "/css/**", "/images/**", "/webjars/**", "/static/**").permitAll() // 静态资源路径
							.anyExchange().permitAll();
				}
		)
				.addFilterAt(customHeaderFilter, SecurityWebFiltersOrder.AUTHENTICATION) // 添加自定义过滤器
				.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.formLogin(ServerHttpSecurity.FormLoginSpec::disable);
		return http.build();
		
		
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("http://localhost:5173");  // 允许来自 Vue 前端的请求
		corsConfig.addAllowedMethod("*");  // 允许所有 HTTP 方法
		corsConfig.addAllowedHeader("*");  // 允许所有请求头
		corsConfig.setAllowCredentials(true);  // 允许带凭证的请求（如果有需要）
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/sse/**", corsConfig);  // 为 SSE 路径单独配置 CORS
		source.registerCorsConfiguration("/**", corsConfig);  // 为其他路径配置 CORS
		return source;
	}
}
