package tank.demo20.strategy;


import tank.demo20.facade.Tank;

import java.io.Serializable;

/**
 * 开火策略
 * @author zhangling  2021/12/9 22:37
 */
public interface FireStrategy extends Serializable {

    /**
     * 坦克开火
     */
    void fire(Tank tank);
}
