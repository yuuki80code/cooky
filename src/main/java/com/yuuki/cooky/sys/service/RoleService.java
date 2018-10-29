package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "role")
public interface RoleService extends IService<SysRole> {

    @Cacheable(key = "'id_'+#p0")
    List<SysRole> findUserRole(Long userid);


    ResponseVo addRole(SysRole role,Long[] menuIds);

    ResponseVo updateRole(SysRole role,Long[] menuIds);

    List<Long> findMenuByRole(Long id);

    ResponseVo deleteRole(Long roleId);

}
