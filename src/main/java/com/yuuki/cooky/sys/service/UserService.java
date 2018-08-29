package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.entity.UserWithRole;

import java.util.List;

public interface UserService extends IService<SysUser> {

    ResponseVo login(String username,String password);

    SysUser findByName(String userName);

    List<SysUser> findUserMsg(SysUser user);

    void addUser(SysUser user,Long[] roles);

    UserWithRole findUserWithRole(SysUser user);

    void updateUser(SysUser user,Long[] roles);

    void deleteUser(SysUser user);
}
