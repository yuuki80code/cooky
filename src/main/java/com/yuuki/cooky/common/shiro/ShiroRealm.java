package com.yuuki.cooky.common.shiro;

import com.yuuki.cooky.common.oauth2.OAuth2Token;
import com.yuuki.cooky.common.oauth2.TokenUtil;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.entity.SysRole;
import com.yuuki.cooky.sys.entity.SysUser;
import com.yuuki.cooky.sys.service.MenuService;
import com.yuuki.cooky.sys.service.RoleService;
import com.yuuki.cooky.sys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShiroRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 自定义用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userid = TokenUtil.getUsernId(principalCollection.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<SysRole> roleList = this.roleService.findUserRole(userid);
        Set<String> roleSet = roleList.stream().map(SysRole::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<SysMenu> permissionList = this.menuService.findUserPermissions(userid);
        Set<String> permissionSet = new HashSet<>();
        for (SysMenu m : permissionList) {
            permissionSet.add(m.getPerms());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }



    /**
     * 自定义用户认证
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        Long userid = TokenUtil.getUsernId(token);
        if (userid == null) {
            throw new AuthenticationException("token invalid");
        }

        SysUser user = userService.selectByKey(userid);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! TokenUtil.verify(token, userid, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
