package tank.demo15.strategy;


import tank.demo15.Bullet;
import tank.demo15.Tank;

/**
 * @author zhangling  2021/12/9 22:53
 */
public class TwoBulletFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank){
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tank.getTf().getBulletList().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf()));

        tank.getTf().getBulletList().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf()));
        // new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf());
    }
}
