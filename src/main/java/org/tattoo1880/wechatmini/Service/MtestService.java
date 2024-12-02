package org.tattoo1880.wechatmini.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tattoo1880.wechatmini.Entity.Mtest;
import org.tattoo1880.wechatmini.Repositories.MtestRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MtestService {
	
	
	@Autowired
	private MtestRepository mtestRepository;
	
	
	public Mono<Mtest> save(Mtest mtest) {
		
		log.warn("mtest:{}",mtest);
		return mtestRepository.insert(mtest);
		
	}
	
	public Flux<Mtest> findAll() {
		return mtestRepository.findAll();
	}
	
	
	public Mono<String> deleteall() {
		return mtestRepository.deleteAll().thenReturn("delete all success")
				.onErrorReturn("delete all failed");
	}
	
}
