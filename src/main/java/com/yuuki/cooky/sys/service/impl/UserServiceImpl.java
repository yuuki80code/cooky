package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.oauth2.TokenUtil;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.common.util.MD5Util;
import com.yuuki.cooky.sys.dao.SysUserMapper;
import com.yuuki.cooky.sys.dao.SysUserRoleMapper;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.entity.SysUserRole;
import com.yuuki.cooky.sys.entity.UserWithRole;
import com.yuuki.cooky.sys.service.UserRoleService;
import com.yuuki.cooky.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseService<SysUser> implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Override
    public ResponseVo login(String username, String password) {

        password = MD5Util.encrypt(username.toLowerCase(), password);

        SysUser sysUser = findByName(username);

        if(null == sysUser){
            return ResponseVo.error("用户名不存在");
        }
        if(!sysUser.getPassword().equals(password)){
            return ResponseVo.error("密码错误");
        }
        if(sysUser.getStatus().equals(0)){
            return ResponseVo.error("账号已锁定");
        }
        String token = TokenUtil.sign(sysUser.getUserId(), sysUser.getPassword());
        return ResponseVo.ok("登陆成功",token);
    }

    @Override
    public SysUser findByName(String userName) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
        List<SysUser> list = this.selectByExample(example);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public List<SysUser> findUserMsg(SysUser user) {
        return sysUserMapper.findUserMsg(user);
    }

    @Transactional
    @Override
    public void addUser(SysUser user, Long[] roles) {
        user.setCrateTime(new Date());
        user.setModifyTime(new Date());
        user.setPassword(MD5Util.encrypt(user.getUsername(),user.getPassword()));
        this.save(user);
        saveOrUpdateRole(user, roles);
    }

    @Override
    public UserWithRole findUserWithRole(SysUser user) {
        List<UserWithRole> userWithRole = sysUserMapper.findUserWithRole(user);
        List<Long> roleList = new ArrayList<>();
        for (UserWithRole uwr : userWithRole) {
            roleList.add(uwr.getRoleId());
        }
        if (userWithRole.size() == 0) {
            return null;
        }
        UserWithRole uwr = userWithRole.get(0);
        uwr.setRoles(roleList);
        return uwr;
    }

    @Override
    @Transactional
    public void updateUser(SysUser user, Long[] roles) {
        user.setPassword(null);
        user.setUsername(null);
        user.setModifyTime(new Date());
        this.updateNotNull(user);
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andCondition("user_id=", user.getUserId());
        this.userRoleMapper.deleteByExample(example);
        saveOrUpdateRole(user, roles);
    }

    @Override
    public void deleteUser(SysUser user) {
        this.delete(user.getUserId());
    }

    private void saveOrUpdateRole(SysUser user,Long[] roles){
        Arrays.stream(roles).forEach(role->{
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(role);
            userRoleService.save(userRole);
        });

    }


}
