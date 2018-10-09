package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.entity.SysUserRole;
import com.yuuki.cooky.sys.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleServiceImpl extends BaseService<SysUserRole> implements UserRoleService {


    @Override
    public void deleteByUserId(Long userId) {
        this.batchDelete(Arrays.asList(userId.toString()),"userId",SysUserRole.class);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        this.batchDelete(Arrays.asList(roleId.toString()),"roleId",SysUserRole.class);
    }
}
