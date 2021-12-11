package com.ling.observer;

import com.ling.facade.Tank;

/**
 * @author zhangling  2021/12/11 21:56
 */
public class TankFireHandler implements TankFireObserver{

    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank tank = event.getSource();
        tank.fire();
    }
}
