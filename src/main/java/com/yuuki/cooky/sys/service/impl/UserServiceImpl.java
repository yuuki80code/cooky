package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.oauth2.OAuth2Token;
import com.yuuki.cooky.common.oauth2.TokenUtil;
import com.yuuki.cooky.common.service.IService;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.common.util.MD5Util;
import com.yuuki.cooky.sys.dao.SysUserMapper;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl extends BaseService<SysUser> implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

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
}
