package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.model.Tree;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.common.util.TreeUtil;
import com.yuuki.cooky.sys.entity.SysDept;
import com.yuuki.cooky.sys.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl extends BaseService<SysDept> implements DeptService {
    @Override
    public ResponseVo deptTreeData() {
        List<SysDept> depts = this.selectAll();
        List<Tree<SysDept>> trees = new ArrayList<>();
        depts.forEach(dept->{
            Tree<SysDept> tree = new Tree<>();
            tree.setTitle(dept.getDeptName());
            tree.setParentId(dept.getParentId().intValue());
            tree.setId(dept.getDeptId().intValue());
            trees.add(tree);
        });
        List<Tree<SysDept>> build = TreeUtil.buildList(trees,"0");

        return ResponseVo.ok(build);
    }
}
