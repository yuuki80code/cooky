package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.dao.SysRoleMapper;
import com.yuuki.cooky.sys.dao.SysRoleMenuMapper;
import com.yuuki.cooky.sys.entity.SysRole;
import com.yuuki.cooky.sys.entity.SysRoleMenu;
import com.yuuki.cooky.sys.service.RoleMenuService;
import com.yuuki.cooky.sys.service.RoleService;
import com.yuuki.cooky.sys.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseService<SysRole> implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<SysRole> findUserRole(Long userid){
        return sysRoleMapper.findUserRole(userid);
    }

    @Override
    @Transactional
    public ResponseVo editRole(SysRole role,Long[] menuIds) {
        if(role.getRoleId() == 0){
            role.setCreateTime(new Date());
            this.save(role);
            this.setRoleMenu(role, menuIds);
            return ResponseVo.ok("新增成功");
        }else {
            role.setModifyTime(new Date());
            this.updateNotNull(role);
            Example example = new Example(SysRoleMenu.class);
            example.createCriteria().andCondition("role_id=", role.getRoleId());
            this.sysRoleMenuMapper.deleteByExample(example);
            setRoleMenu(role, menuIds);
            return ResponseVo.ok("修改成功");
        }
    }
    @Override
    @Transactional
    public ResponseVo deleteRole(Long roleId) {
        this.delete(roleId);
        roleMenuService.deleteByRoleId(roleId);
        userRoleService.deleteByRoleId(roleId);
        return ResponseVo.ok("删除成功");
    }


    private void setRoleMenu(SysRole role,Long[] menuIds){
        Arrays.stream(menuIds).forEach(id -> {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setMenuId(id);
            rm.setRoleId(role.getRoleId());
            sysRoleMenuMapper.insert(rm);
        });
    }

    @Override
    public List<Long> findMenuByRole(Long id) {
        return sysRoleMenuMapper.findMenuByRole(id);
    }
}
