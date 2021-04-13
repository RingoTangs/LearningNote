package com.ymy.boot.utils;

import java.util.List;

public interface RecursionEntity<T> {

    Integer getId();

    Integer getParentId();

    void setChildren(List<T> children);
}
