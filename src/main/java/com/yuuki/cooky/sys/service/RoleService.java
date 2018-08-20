package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysRole;

import java.util.List;

public interface RoleService extends IService<SysRole> {

    List<SysRole> findUserRole(Long userid);
}
