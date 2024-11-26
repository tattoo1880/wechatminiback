package org.tattoo1880.wechatmini.Repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.tattoo1880.wechatmini.Entity.Mp4;
import org.tattoo1880.wechatmini.Entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,String> {
	
	//todo save or update

}
