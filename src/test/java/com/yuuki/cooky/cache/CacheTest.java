package com.yuuki.cooky.cache;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheTest {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TestService testService;

    @Test
    public void test(){
//        stringRedisTemplate.opsForValue().set("test","123");
        testService.txxx(123);
    }


    @Cacheable(value = "test",keyGenerator = "keyGenerator")
    public String cache(String a){
        return "aaaa";
    }

}
