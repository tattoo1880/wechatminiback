package org.tattoo1880.wechatmini.Components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AppReactiveUserDetailService implements ReactiveUserDetailsService {
	
	
	@Autowired
	DatabaseClient databaseClient;
	
	
	@Override
	public Mono<UserDetails> findByUsername(String username) {
		
		
		
		String sql = "SELECT u.username, u.password, " +
				"GROUP_CONCAT(DISTINCT r.name) AS roles, " +
				"GROUP_CONCAT(DISTINCT p.value) AS permissions " +
				"FROM user u " +
				"JOIN user_role ur ON u.id = ur.user_id " +
				"JOIN roles r ON ur.role_id = r.id " +
				"JOIN role_perm rp ON r.id = rp.role_id " +
				"JOIN perm p ON rp.perm_id = p.id " +
				"WHERE u.username = :username " +
				"GROUP BY u.username";
	
		//! 根据username 查询用所有的权限和角色
		Mono<UserDetails> userDetailsMono = databaseClient.sql(sql)
				.bind(0, username)
				.fetch()
				.first()
				.map(
						map -> {
							UserDetails build = User.builder()
									.username(username)
									.password( "{noop}" +map.get("password").toString())
									.authorities("download", "view", "delete")
									.roles("admin", "sale")
									.build();
							
							return build;
						}
				);
		
		return userDetailsMono;
		
	}
}
