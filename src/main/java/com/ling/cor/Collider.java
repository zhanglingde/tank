package com.ling.cor;

import com.ling.mediator.GameObject;

/**
 * 碰撞器
 * @author ling
 */
public interface Collider {

    /**
     * o1 碰撞 o2
     * @param o1
     * @param o2
     * @return true 继续向下
     */
    boolean collide(GameObject o1, GameObject o2);

}
