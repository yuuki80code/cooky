package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.controller.BaseController;
import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.oauth2.TokenUtil;
import com.yuuki.cooky.common.util.MD5Util;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Security;

@RestController
public class LoginController extends BaseController {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseVo login(String username, String password){
        return userService.login(username, password);
    }

    @PostMapping("/refresh/{token}")
    public ResponseVo refresh(@PathVariable("token")String token) {
        return userService.refreshToken(token);
    }


}
