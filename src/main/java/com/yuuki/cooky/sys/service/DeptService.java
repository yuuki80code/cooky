package com.yuuki.cooky.sys.service;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.sys.entity.SysDept;

public interface DeptService extends IService<SysDept> {

    ResponseVo deptTreeTableData();

    ResponseVo deptTreeData();

    ResponseVo addDept(SysDept dept);

}
