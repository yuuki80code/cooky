package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.model.Tree;
import com.yuuki.cooky.common.model.TreeTable;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.common.util.TreeUtil;
import com.yuuki.cooky.sys.dao.SysDeptMapper;
import com.yuuki.cooky.sys.entity.SysDept;
import com.yuuki.cooky.sys.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl extends BaseService<SysDept> implements DeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysDept> getDeptList() {
        List<SysDept> depts = this.selectAll();
//        List<TreeTable<SysDept>> trees = new ArrayList<>();
//        depts.forEach(dept->{
//            TreeTable<SysDept> tree = new TreeTable<>();
//            tree.setParentId(dept.getParentId().intValue());
//            tree.setId(dept.getDeptId().intValue());
//            tree.setObj(dept);
//            trees.add(tree);
//        });
//        List<TreeTable<SysDept>> build = TreeUtil.buildTreeTableList(trees,"0");

        return depts;
    }

    @Override
    public ResponseVo addDept(SysDept dept) {
        dept.setCreateTime(new Date());
        this.save(dept);
        return ResponseVo.ok("添加成功");
    }

    @Override
    public ResponseVo updateDept(SysDept dept) {
        this.updateNotNull(dept);
        return ResponseVo.ok("更新成功");
    }


    @Override
    @Transactional
    public ResponseVo deleteDept(Long id) {
        this.delete(id);
        // 删除后连带子部门一起删除
        sysDeptMapper.deleteChild(id);
        return ResponseVo.ok("删除成功");
    }
}
