package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.entity.SysRoleMenu;
import com.yuuki.cooky.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RoleMenuServiceImpl extends BaseService<SysRoleMenu> implements RoleMenuService {

    @Override
    public void deleteByRoleId(Long roleId) {
        this.batchDelete(Arrays.asList(roleId.toString()),"roleId",SysRoleMenu.class);
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        this.batchDelete(Arrays.asList(menuId.toString()),"menuId",SysRoleMenu.class);
    }
}
