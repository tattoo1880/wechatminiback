package org.tattoo1880.wechatmini.Repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.tattoo1880.wechatmini.Entity.Mp4;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface Mp4Repository extends ReactiveCrudRepository<Mp4, Long> {
	
	@Query("SELECT * FROM mp4 WHERE create_time > :lastCheckedTime")
	Flux<Mp4> findNewEntries(@Param("lastCheckedTime") LocalDateTime lastCheckedTime);
}
