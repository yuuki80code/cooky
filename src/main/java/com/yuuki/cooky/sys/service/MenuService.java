package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = "menu")
public interface MenuService extends IService<SysMenu> {


    List<SysMenu> findUserPermissions(Long userid);


    ResponseVo addMenu(SysMenu menu);

    ResponseVo updateMenu(SysMenu menu);

    ResponseVo deleteMenu(Long id);

    @Cacheable(key = "#p0")
    Map<String, Map<String, Object>> getUserMenu(Long userId);

}
