package com.yuuki.cooky.common.controller;

import com.github.pagehelper.PageInfo;
import com.yuuki.cooky.common.model.ResponseVo;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected ResponseVo pageData(PageInfo<?> pageInfo){
        Map<String,Object> map = new HashMap<>();
        map.put("page",pageInfo.getPageNum());
        map.put("pageSize",pageInfo.getPageSize());
        map.put("total",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return ResponseVo.ok(map);
    }


}
