package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysRole;

import java.util.List;

public interface RoleService extends IService<SysRole> {

    List<SysRole> findUserRole(Long userid);


    ResponseVo addRole(SysRole role,Long[] menuIds);

    ResponseVo updateRole(SysRole role,Long[] menuIds);

    List<Long> findMenuByRole(Long id);

    ResponseVo deleteRole(Long roleId);

}
