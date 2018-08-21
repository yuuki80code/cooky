package com.yuuki.cooky.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuuki.cooky.common.controller.BaseController;
import com.yuuki.cooky.common.model.Params;
import com.yuuki.cooky.common.model.QueryRequest;
import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {


    @Autowired
    UserService userService;

    @PostMapping("/list")
    public ResponseVo list(Params params,SysUser user){
        PageHelper.startPage(params.getPage(),params.getPageSize());
        List<SysUser> users = userService.findUserMsg(user);

        PageInfo<SysUser> userPageInfo = new PageInfo<>(users);

        return this.pageData(userPageInfo);
    }

    @PostMapping("/add")
    public ResponseVo addUser(SysUser user,Long[] roles) {
        if(user.getStatus().equalsIgnoreCase("true")){
            user.setStatus("1");
        }else{
            user.setStatus("2");
        }
        userService.addUser(user,roles);
        return ResponseVo.ok("success");
    }

    public ResponseVo getUser(SysUser user){

        return null;
    }
}
