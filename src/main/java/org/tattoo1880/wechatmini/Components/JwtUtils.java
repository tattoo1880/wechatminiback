package org.tattoo1880.wechatmini.Components;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	
	// 秘钥，通常保存在安全的地方
	private static final String SECRET_KEY = "wolegecao";
	// JWT 默认过期时间 30 分钟
	private static final long EXPIRATION_TIME = 60 * 60 * 1000 * 24;
//	private static final long EXPIRATION_TIME = 1000 *30;
	
	// 创建 JWT token
	public String createToken(String username) {
		// 使用 HMAC256 算法生成签名
		Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
		
		return JWT.create()
				.withSubject(username)  // 设置 token 的主题（可以是用户名等）
				.withIssuedAt(new Date()) // 设置 token 签发时间
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
				.sign(algorithm); // 使用签名算法
	}
	
	// 验证 token
	public boolean verifyToken(String token) {
		System.out.println("token: " + token);
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			JWT.require(algorithm).build().verify(token);
			return true; // 验证成功
		} catch (TokenExpiredException e) {
			System.out.println("Token 已过期: " + e.getMessage());
		} catch (JWTVerificationException e) {
			System.out.println("Token 验证失败: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("其他异常: " + e.getMessage());
		}
		return false;
		
	}
	// 解析 token
	public String parseToken(String token) {
		return JWT.decode(token).getSubject();
	}
	
	// 判断 token 是否过期,如果过期返回msg:过期
	public String isTokenExpired(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			JWT.require(algorithm).build().verify(token);
			return "未过期";
		} catch (TokenExpiredException e) {
			return "过期";
		} catch (JWTVerificationException e) {
			return "验证失败";
		}
	}
}
