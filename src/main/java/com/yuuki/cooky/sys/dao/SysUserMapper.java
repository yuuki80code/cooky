package com.yuuki.cooky.sys.dao;


import com.yuuki.cooky.common.config.MyMapper;
import com.yuuki.cooky.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {

    List<SysUser> findUserMsg(SysUser sysUser);
}