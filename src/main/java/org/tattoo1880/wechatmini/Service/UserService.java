package org.tattoo1880.wechatmini.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.sql.Where;
import org.springframework.stereotype.Service;
import org.tattoo1880.wechatmini.Components.JwtUtils;
import org.tattoo1880.wechatmini.Entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class UserService {
	
	@Autowired
	JwtUtils jwtUtils;
	
	private final R2dbcEntityTemplate r2dbcEntityTemplate;
	private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;
	
	public UserService(R2dbcEntityTemplate r2dbcEntityTemplate, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
		this.r2dbcEntityTemplate = r2dbcEntityTemplate;
		this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
	}
	
	
	
	
	//todo save or update
	
	public Mono<User>saveorupdate(User user){
		
		System.out.println("saveorupdate");
		System.out.println(user);
		System.out.println("saveorupdate");
				//todo 先在数据库中查询是否存在该用户，依靠email 和 username 匹配 如果存在则更新，如果不存在则插入
				//todo 1.查询
		String currentUsername = user.getUsername();
		String mail = user.getEmail();
		return r2dbcEntityTemplate.select(User.class)
				.matching(Query.query(Criteria.where("username").is(currentUsername)))
				.one()
				.flatMap(
						user1 -> {
							//todo 2.更新
							user.setId(user1.getId());
							String createtime = user1.getCreateTime().toString();
							user.setCreateTime(Instant.parse(createtime));
							//!更新时间
							user.setUpdateTime(Instant.now());
							return r2dbcEntityTemplate.update(user);
						}
				)
				.switchIfEmpty(
						//todo 3.插入
						r2dbcEntityTemplate.insert(user)
				)
				.doOnSuccess(
						user2 -> {
							System.out.println("save success");
						}
				)
				.doOnError(
						throwable -> {
							System.out.println("save fail");
						}
				);
		
	}
	
	
	//todo 根据username 查询用户
	public Mono<User> findByUsername(String username){
		return r2dbcEntityTemplate.select(User.class)
				.matching(Query.query(Criteria.where("username").is(username)))
				.one();
	}
	
	
	//todo 查询所有用户
	public Flux<User> findAll(){
		return r2dbcEntityTemplate.select(User.class)
				.all();
	}
	
	
	//todo 根据id 删除用户
	public Mono<Void> deleteById(Long id){
		return r2dbcEntityTemplate.delete(User.class)
				.matching(Query.query(Criteria.where("id").is(id)))
				.all()
				.then();
	}
	
	//todo 更新用户
	public Mono<User> update(User user){
		return r2dbcEntityTemplate.update(user);
	}
	
	
	//todo login
	public Mono<User> login(String username, String password){
		System.out.println(username + password);
		return r2dbcEntityTemplate.select(User.class)
				.matching(Query.query(Criteria.where("username").is(username).and("password").is(password)))
				.one()
				//todo 如果找到了,添加sec_token字段的值的
				.flatMap(
						user -> {
							
							//todo 使用jwt生成token
							//todo 1.生成token
							
							String Jwt = jwtUtils.createToken(username);
							user.setSecToken(Jwt);
							//todo 2.更新
							return r2dbcEntityTemplate.update(user);
							
						}
				)
				//todo 如果没有找到
				.switchIfEmpty(
						Mono.empty()
				);
	}
	
}
