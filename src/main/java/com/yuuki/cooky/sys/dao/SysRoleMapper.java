package com.yuuki.cooky.sys.dao;

import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysRoleMapper extends MyMapper<SysRole> {

    List<SysRole> findUserRole(Long userid);

}