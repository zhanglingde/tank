package com.ling.decorator;

import com.ling.facade.GameModel;
import com.ling.mediator.GameObject;

import java.awt.*;

/**
 * 装饰器模式
 * 对 GameObject 进行包装
 * @author zhangling  2021/12/11 16:18
 */
public abstract class GODecorator extends GameObject {
    // 聚合一个 GameObject,GODecorator 可以装饰 坦克，子弹，墙等
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
        GameModel.getInstant().add(this);
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
