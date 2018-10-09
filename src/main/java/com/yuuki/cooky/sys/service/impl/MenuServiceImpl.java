package com.yuuki.cooky.sys.service.impl;

import com.yuuki.cooky.common.model.ResponseVo;
import com.yuuki.cooky.common.service.impl.BaseService;
import com.yuuki.cooky.sys.dao.SysMenuMapper;
import com.yuuki.cooky.sys.entity.SysMenu;
import com.yuuki.cooky.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseService<SysMenu> implements MenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

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
    public ResponseVo deleteMenu(Long id) {
        this.delete(id);
        return ResponseVo.ok("删除成功");
    }
}
