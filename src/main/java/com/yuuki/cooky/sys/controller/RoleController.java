package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysRole;
import com.yuuki.cooky.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public ResponseVo getRoleList(){

        return ResponseVo.ok(roleService.selectAll());
    }

    @PostMapping("/edit")
    public ResponseVo editRole(SysRole role,Long[] menuIds) {

        return roleService.editRole(role,menuIds);
    }

    @GetMapping("/menus")
    public ResponseVo getMenuByRole(Long id) {
        return ResponseVo.ok(roleService.findMenuByRole(id));
    }

    @PostMapping("/delete")
    public ResponseVo deleteRole(Long roleId) {
        return roleService.deleteRole(roleId);
    }

}
