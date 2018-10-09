package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.model.Tree;
import com.yuuki.cooky.common.model.TreeTable;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.common.util.TreeUtil;
import com.yuuki.cooky.sys.entity.SysDept;
import com.yuuki.cooky.sys.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl extends BaseService<SysDept> implements DeptService {

    @Override
    public ResponseVo deptTreeTableData() {
        List<SysDept> depts = this.selectAll();
        List<TreeTable<SysDept>> trees = new ArrayList<>();
        depts.forEach(dept->{
            TreeTable<SysDept> tree = new TreeTable<>();
            tree.setParentId(dept.getParentId().intValue());
            tree.setId(dept.getDeptId().intValue());
            tree.setObj(dept);
            trees.add(tree);
        });
        List<TreeTable<SysDept>> build = TreeUtil.buildTreeTableList(trees,"0");

        return ResponseVo.ok(build);
    }

    @Override
    public ResponseVo deptTreeData() {
        List<SysDept> depts = this.selectAll();
        List<Tree<SysDept>> trees = new ArrayList<>();
        depts.forEach(dept->{
            Tree<SysDept> tree = new Tree<>();
            tree.setTitle(dept.getDeptName());
            tree.setParentId(dept.getParentId().intValue());
            tree.setId(dept.getDeptId().intValue());
            tree.setExtraData(dept.getCreateTime());
            trees.add(tree);
        });
        List<Tree<SysDept>> build = TreeUtil.buildList(trees,"0");

        return ResponseVo.ok(build);
    }




    @Override
    public ResponseVo addOrUpdateDept(SysDept dept) {
        if(dept.getDeptId() == -1){
            dept.setCreateTime(new Date());
            this.save(dept);
            return ResponseVo.ok("添加成功");
        }else {
            this.updateNotNull(dept);
            return ResponseVo.ok("更新成功");
        }

    }

    @Override
    public ResponseVo deleteDept(Long id) {
        this.delete(id);
        return ResponseVo.ok("删除成功");
    }
}
