package com.ling.strategy;

import com.ling.facade.Bullet;
import com.ling.tank.Dir;
import com.ling.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:49
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tank.getGm().add(new Bullet(bX, bY, dir, tank.getGroup(), tank.getGm()));
        }
    }
}