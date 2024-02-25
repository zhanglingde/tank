package tank.demo22.demo21.strategy;


import tank.demo22.demo21.Dir;
import tank.demo22.demo21.decorator.BulletDecorator;
import tank.demo22.demo21.facade.Bullet;
import tank.demo22.demo21.facade.Tank;

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
            new BulletDecorator(
                    new Bullet(bX, bY, dir, tank.getGroup()));
            // new RectDecorator(
            //         new TailDecorator(
            //                 new Bullet(bX, bY, dir, tank.getGroup())));
        }
    }
}
