package com.yuuki.cooky.sys.dao;

import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper extends MyMapper<SysRoleMenu> {


    List<Long> findMenuByRole(Long roleId);
}