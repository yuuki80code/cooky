package com.yuuki.cooky.sys.dao;

import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface SysMenuMapper extends MyMapper<SysMenu> {

    List<SysMenu> findUserPermissions(Long userid);

    void changeMenuToTop(Long id);

    List<SysMenu> findUserMenus(Long userId);
}