package com.yuuki.cooky.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserWithRole extends SysUser {


    private static final long serialVersionUID = -2132513488638904723L;

    private Long RoleId;

    private List<Long> roles;

}
