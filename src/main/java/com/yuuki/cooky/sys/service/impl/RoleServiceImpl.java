package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.dao.SysRoleMapper;
import com.yuuki.cooky.sys.entity.SysRole;
import com.yuuki.cooky.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseService<SysRole> implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findUserRole(Long userid){
        return sysRoleMapper.findUserRole(userid);
    }
}
