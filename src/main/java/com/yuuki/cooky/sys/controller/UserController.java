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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/userwithrole")
  public ResponseVo getUserWithRole(SysUser user){

    return ResponseVo.ok(userService.findUserWithRole(user));
  }

  @PostMapping("/update")
  public ResponseVo updateUser(SysUser user, Long[] roles, BindingResult bindingResult){
    if(user.getStatus().equalsIgnoreCase("true")){
      user.setStatus("1");
    }else{
      user.setStatus("2");
    }
    userService.updateUser(user, roles);
    return ResponseVo.ok("修改成功");
  }
  @PostMapping("/delete")
  public ResponseVo deleteUser(SysUser user){
    userService.deleteUser(user);
    return ResponseVo.ok("删除成功");
  }

}
