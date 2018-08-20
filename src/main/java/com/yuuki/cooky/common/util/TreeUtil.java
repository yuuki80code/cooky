package com.yuuki.cooky.common.util;

import com.yuuki.cooky.common.model.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    public static <T> Tree<T> build(List<Tree<T>> nodes) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId()+"";
            if (pid == null || "0".equals(pid)) {
                topNodes.add(children);
                return;
            }
            for (Tree<T> parent : nodes) {
                String id = parent.getId()+"";
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                    return;
                }
            }
        });

        Tree<T> root = new Tree<>();
        root.setId(0);
        root.setParentId(0);
        root.setChilds(topNodes);
        root.setTitle("根节点");
        return root;
    }

    public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, String idParam) {
        if (nodes == null) {
            return null;
        }
        List<Tree<T>> topNodes = new ArrayList<>();
        nodes.forEach(children -> {
            String pid = children.getParentId()+"";
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(children);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId()+"";
                if (id != null && id.equals(pid)) {
                    parent.getChilds().add(children);
                }
            });
        });
        return topNodes;
    }
}
