package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public ResponseVo getRoleList(){

        return ResponseVo.ok(roleService.selectAll());
    }



}
