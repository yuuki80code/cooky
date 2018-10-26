package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @Autowired
    MenuService menuService;

    @RequestMapping("/list")
    public ResponseVo getMenuList() {

        return ResponseVo.ok(menuService.selectAll());
    }


    // 不知道为什么不加BindingResult 就会报400 - -
    @PostMapping("/add")
    @RequiresPermissions("menu_add")
    public ResponseVo add(SysMenu menu, BindingResult bindingResult) {

        return menuService.addMenu(menu);
    }

    @PostMapping("/update")
    @RequiresPermissions("menu_edit")
    public ResponseVo update(SysMenu menu, BindingResult bindingResult) {

        return menuService.updateMenu(menu);
    }

    @PostMapping("/delete")
    @RequiresPermissions("menu_delete")
    public ResponseVo delete(Long id) {
        return menuService.deleteMenu(id);
    }


    @RequestMapping("/usermenu")
    @RequiresAuthentication
    public ResponseVo getUserMenu(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return ResponseVo.ok(menuService.getUserMenu(authorization));
    }

}
