package com.ling.cor;

import com.ling.facade.Bullet;
import com.ling.facade.Tank;
import com.ling.mediator.GameObject;

/**
 * 子弹坦克碰撞
 * @author zhangling
 * @date 2021/12/10 8:02 下午
 */
public class BulletTankCollider implements Collider{

    /**
     * 子弹坦克碰撞
     * @param o1
     * @param o2
     * @return 返回 false 发生碰撞，true 未碰撞，继续向下执行
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (bullet.collideWith(tank)) {
                // 发生碰撞，返回 false,不继续向下判断
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
