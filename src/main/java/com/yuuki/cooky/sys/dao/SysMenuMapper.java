package com.yuuki.cooky.sys.dao;

import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysMenu;

import java.util.List;

public interface SysMenuMapper extends MyMapper<SysMenu> {

    List<SysMenu> findUserPermissions(Long userid);
}