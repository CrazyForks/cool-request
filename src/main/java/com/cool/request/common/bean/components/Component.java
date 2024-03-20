package com.cool.request.common.bean.components;

import com.cool.request.components.ComponentType;

/**
 * 基本组件
 */
public interface Component {
    String getId();

    public void setId(String id);

    ComponentType getComponentType();
}
