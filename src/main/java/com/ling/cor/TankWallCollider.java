package com.ling.cor;

import com.ling.facade.Tank;
import com.ling.facade.Wall;
import com.ling.mediator.GameObject;
import com.ling.tank.Group;

/**
 * 坦克墙碰撞器
 *
 * @author zhangling  2021/12/11 10:43
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRect().intersects(wall.getRect())) {
                if (tank.getGroup() == Group.BAD) {
                    tank.back();
                } else {
                    // Good Tank 碰撞墙进行处理
                }
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
