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
    public ResponseVo getDeptList(){
        return ResponseVo.ok(deptService.getDeptList());
    }

//    @GetMapping("/treetable")
//    public ResponseVo treeTableList(){
//        return deptService.deptTreeTableData();
//    }

    @PostMapping("/add")
    @RequiresPermissions("dept_add")
    public ResponseVo addDept(SysDept dept, BindingResult bindingResult){
        return deptService.addDept(dept);
    }

    @PostMapping("/update")
    @RequiresPermissions("dept_update")
    public ResponseVo updateDept(SysDept dept, BindingResult bindingResult){
        return deptService.updateDept(dept);
    }

    @PostMapping("/delete")
    @RequiresPermissions("dept_delete")
    public ResponseVo deleteDept(Long id) {
        return deptService.deleteDept(id);
    }


}
