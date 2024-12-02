package org.tattoo1880.wechatmini.Config;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class MongoDb {
	
	//todo 启动的时候测试一下mongodb的连接
	@Bean
	public MongoClient mongoClient() {
		// MongoDB connection URI with parameters for connection pooling
		ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/newtest");
		
		// Custom MongoDB settings with connection pooling parameters
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.applyToConnectionPoolSettings(builder ->
						builder.maxConnectionIdleTime(60000, java.util.concurrent.TimeUnit.MILLISECONDS) // 最大空闲时间
								.maxConnectionLifeTime(60000, java.util.concurrent.TimeUnit.MILLISECONDS) // 最大连接生命周期
								.maxSize(100) // 最大连接数
								.minSize(10) // 最小连接数
				)
				.build();
		
		return MongoClients.create(settings); // 创建 MongoClient 实例
	}
	
	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
		return new ReactiveMongoTemplate(mongoClient, "newtest");
	}
	
	
}
