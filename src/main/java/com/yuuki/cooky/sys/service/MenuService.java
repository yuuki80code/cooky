package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysMenu;

import java.util.List;

public interface MenuService extends IService<SysMenu> {

    List<SysMenu> findUserPermissions(Long userid);
}
