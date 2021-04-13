package com.ymy.boot.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 递归封装列表
 */
public class RecursionList<T extends RecursionEntity> {

    /**
     * 将列表 封装成 ==> 带多级菜单的列表
     *
     * @param list     所有的列表
     * @param parentId 一级菜单的 parentId（用来获取一级菜单）
     * @return
     */
    public List<T> levelList(List<T> list, Integer parentId) {
        List<T> ret = list.stream().filter(item -> item.getParentId().equals(parentId))
                .map(item -> {
                    item.setChildren(getChildren(item, list));
                    return item;
                }).collect(Collectors.toList());
        System.out.println(ret);
        return ret;
    }

    /**
     * 递归获得子部门
     *
     * @param root 当前的部门
     * @param list 所有的部门列表
     * @return
     */
    private List<T> getChildren(T root, List<T> list) {
        return list.stream().filter(item -> item.getParentId().equals(root.getId()))
                .map(item -> {
                    item.setChildren(getChildren(item, list));
                    return item;
                }).collect(Collectors.toList());
    }
}
