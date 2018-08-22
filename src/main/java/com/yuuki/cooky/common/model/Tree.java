package com.yuuki.cooky.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {

    private int id;

    private String title;

    private List<Tree<T>> children = new ArrayList<>();

    private Object extraData;

    private int parentId;



}
