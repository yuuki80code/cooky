package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.oauth2.TokenUtil;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.dao.SysMenuMapper;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.service.RoleMenuService;
import com.yuuki.cooky.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseService<SysMenu> implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<SysMenu> findUserPermissions(Long userid) {
        return sysMenuMapper.findUserPermissions(userid);
    }



    @Override
    public ResponseVo addMenu(SysMenu menu) {
        menu.setCreateTime(new Date());
        menu.setModifyTime(new Date());
        this.save(menu);
        return ResponseVo.ok("新增成功");
    }

    @Override
    public ResponseVo updateMenu(SysMenu menu) {
        menu.setModifyTime(new Date());
        this.updateNotNull(menu);
        return ResponseVo.ok("修改成功");
    }

    @Override
    @Transactional
    public ResponseVo deleteMenu(Long id) {
        this.delete(id);
        roleMenuService.deleteByMenuId(id);
        sysMenuMapper.changeMenuToTop(id);
        return ResponseVo.ok("删除成功");
    }

    @Override
    public Map<String, Map<String, Object>> getUserMenu(Long userId) {
        List<SysMenu> userMenus = sysMenuMapper.findUserMenus(userId);
        Map<String,Map<String, Object>> result = new HashMap<>();
        Map<String,Object> menuMap= new HashMap<>();
        Map<String,Object> buttonMap = new HashMap<>();
        userMenus.forEach(menu -> {
            if (menu.getType().equals("0")){
                menuMap.put(menu.getPerms(),true);
            }else {
                buttonMap.put(menu.getPerms(),true);
            }
        });
        result.put("menu",menuMap);
        result.put("button",buttonMap);
        return result;
    }
}
