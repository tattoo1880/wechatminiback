package org.tattoo1880.wechatmini.Repositories;


import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.tattoo1880.wechatmini.Entity.Mp4;

@Repository
public interface Mp4UrlRepositories extends R2dbcRepository<Mp4, Long> {
}
