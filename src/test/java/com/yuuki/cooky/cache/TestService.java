package com.yuuki.cooky.cache;

import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "ttttt")
public interface TestService extends IService<SysMenu> {


    @Cacheable(key = "'id_'+#p0")
    public String txxx(Integer key);
}
