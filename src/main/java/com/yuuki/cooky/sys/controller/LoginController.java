package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.util.MD5Util;
import com.yuuki.cooky.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseVo login(String username,String password){

        return userService.login(username, password);
    }

    @PostMapping("/test")
    @RequiresPermissions("dept:list")
    public ResponseVo testPre(){
        return ResponseVo.ok("have");
    }


}
