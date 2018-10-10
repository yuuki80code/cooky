package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysDept;

import java.util.List;

public interface DeptService extends IService<SysDept> {

    List<SysDept> getDeptList();

    ResponseVo deptTreeData();

    ResponseVo addOrUpdateDept(SysDept dept);

    ResponseVo deleteDept(Long id);

}
