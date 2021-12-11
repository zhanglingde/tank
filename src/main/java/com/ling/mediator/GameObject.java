package com.ling.mediator;

import lombok.Getter;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/10 4:59 下午
 */
@Getter
public abstract class GameObject {

    protected int x, y;

    /**
     * 绘制图形
     * @param g
     */
    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}
