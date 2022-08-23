package com.yoyo.yoyomall.redis;

import com.yoyo.yoyomall.entity.vo.AdminVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void redisT(){

        ValueOperations<String,AdminVo> value = redisTemplate.opsForValue();
        AdminVo admin = value.get("admin");
        System.out.println(admin.getAccount());
    }
}
