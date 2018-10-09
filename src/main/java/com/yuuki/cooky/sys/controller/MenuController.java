package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/edit")
    public ResponseVo edit(SysMenu menu) {

        return menuService.addOrUpdateMenu(menu);
    }

    @PostMapping("/delete")
    public ResponseVo delete(Long id) {
        return menuService.deleteMenu(id);
    }

}