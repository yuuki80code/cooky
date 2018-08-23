package com.yuuki.cooky.sys.controller;

import com.yuuki.cooky.common.controller.BaseController;
import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.sys.entity.SysDept;
import com.yuuki.cooky.sys.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    DeptService deptService;

    @GetMapping("/tree")
    public ResponseVo treeList(){
        return deptService.deptTreeData();
    }

    @GetMapping("/treetable")
    public ResponseVo treeTableList(){
        return deptService.deptTreeTableData();
    }

    @PostMapping("/add")
    public ResponseVo addDept(SysDept dept){
        return deptService.addDept(dept);
    }


}
