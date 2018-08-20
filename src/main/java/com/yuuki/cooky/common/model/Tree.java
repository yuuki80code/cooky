package com.yuuki.cooky.common.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree<T> {

    private int id;

    private String title;

    private List<Tree<T>> childs = new ArrayList<>();

    private int parentId;



}
