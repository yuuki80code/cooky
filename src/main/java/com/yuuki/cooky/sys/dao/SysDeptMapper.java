package com.yuuki.cooky.sys.dao;

import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysDept;

public interface SysDeptMapper extends MyMapper<SysDept> {

    void deleteChild(Long id);


}