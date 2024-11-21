package org.tattoo1880.wechatmini.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.tattoo1880.wechatmini.Components.GetMp4Url;
import org.tattoo1880.wechatmini.Entity.Mp4;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@Service
public class Mp4Service {

	@Autowired
	private GetMp4Url getMp4Url;
	@Autowired
	R2dbcEntityTemplate r2dbcEntityTemplate;
	
	//todo findall
	public Mono<Mp4> findAll(String tartgetUrl) {
		return getMp4Url.getMp4Url(tartgetUrl);
	}
	
	//todo realfindall
	public Flux<Mp4>RealFindAll(){
		return r2dbcEntityTemplate.select(Mp4.class).all();
	}
	
	//todo deleteall
	public Mono<Void> deleteAll(){
		return r2dbcEntityTemplate.delete(Mp4.class).all().then();
	}
	
	//todo deletebyid
	public Mono<?> delteById(Long id){
		return r2dbcEntityTemplate.delete(Mp4.class).matching(query(where("id").is(id))).all();
	}
}
