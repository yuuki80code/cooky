package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.controller.BaseController;
import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysDept;
import com.yuuki.cooky.sys.service.DeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    DeptService deptService;

    @GetMapping("/list")
    @RequiresPermissions("dept:list")
    public ResponseVo getDeptList(){
        return ResponseVo.ok(deptService.getDeptList());
    }

//    @GetMapping("/treetable")
//    public ResponseVo treeTableList(){
//        return deptService.deptTreeTableData();
//    }

    @PostMapping("/edit")
    public ResponseVo editDept(SysDept dept, BindingResult bindingResult){
        return deptService.addOrUpdateDept(dept);
    }

    @PostMapping("/delete")
    public ResponseVo deleteDept(Long id) {
        return deptService.deleteDept(id);
    }


}
