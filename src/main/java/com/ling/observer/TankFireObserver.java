package com.ling.observer;

import java.io.Serializable;

/**
 * @author zhangling  2021/12/11 21:55
 */
public interface TankFireObserver extends Serializable {

    /**
     * 观察坦克开火事件
     * @param event
     */
    void actionOnFire(TankFireEvent event);
}
