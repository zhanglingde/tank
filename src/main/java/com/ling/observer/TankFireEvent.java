package com.ling.observer;

import com.ling.facade.Tank;

/**
 * @author zhangling  2021/12/11 21:53
 */
public class TankFireEvent {
    private Tank tank;

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

    public Tank getSource() {
        return tank;
    }
}
