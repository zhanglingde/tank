package tank.demo16.strategy;


import tank.demo16.facade.Bullet;
import tank.demo16.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:53
 */
public class TwoBulletFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank){
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tank.getGm().getBulletList().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getGm()));

        tank.getGm().getBulletList().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getGm()));
        // new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf());
    }
}
