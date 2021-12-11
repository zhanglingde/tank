package com.ling.strategy;

import com.ling.decorator.RectDecorator;
import com.ling.decorator.TailDecorator;
import com.ling.facade.Bullet;
import com.ling.facade.GameModel;
import com.ling.tank.Dir;
import com.ling.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:49
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new RectDecorator(
                    new TailDecorator(new Bullet(bX, bY, dir, tank.getGroup())));
        }
    }
}
