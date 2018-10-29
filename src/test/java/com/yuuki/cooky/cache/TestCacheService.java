package com.yuuki.cooky.cache;


import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.entity.SysMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestCacheService extends BaseService<SysMenu> implements TestService{

    public String ttt(String key){
        return "xxxxxxxxxxxxxxx";
    }


    @Override
    public String txxx(Integer key) {
        return null;
    }
}
