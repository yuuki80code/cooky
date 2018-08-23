package com.yuuki.cooky.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class TreeTable<T> {

    private int id;

    private T obj;

    private int parentId;

    private List<TreeTable<T>> children = new ArrayList<>();

}
