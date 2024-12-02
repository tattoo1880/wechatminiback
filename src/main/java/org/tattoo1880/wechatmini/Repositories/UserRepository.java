package org.tattoo1880.wechatmini.Repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;
import org.tattoo1880.wechatmini.Entity.User;


@Repository
@RedisHash("user")
public interface UserRepository extends KeyValueRepository<User,String> {


}
