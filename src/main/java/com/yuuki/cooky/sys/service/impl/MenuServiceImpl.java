package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.dao.SysMenuMapper;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.service.MenuService;
import com.yuuki.cooky.sys.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public ResponseVo addOrUpdateMenu(SysMenu menu) {
        if(menu.getMenuId() == 0){
            menu.setCreateTime(new Date());
            menu.setModifyTime(new Date());
            this.save(menu);
            return ResponseVo.ok("新增成功");
        }else {
            menu.setModifyTime(new Date());
            this.updateNotNull(menu);
            return ResponseVo.ok("修改成功");
        }

    }

    @Override
    @Transactional
    public ResponseVo deleteMenu(Long id) {
        this.delete(id);
        roleMenuService.deleteByMenuId(id);
        sysMenuMapper.changeMenuToTop(id);
        return ResponseVo.ok("删除成功");
    }
}
