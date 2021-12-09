package com.ling.strategy;

import com.ling.tank.Tank;

/**
 * 开火策略
 * @author zhangling  2021/12/9 22:37
 */
public interface FireStrategy {

    /**
     * 坦克开火
     */
    void fire(Tank tank);
}
