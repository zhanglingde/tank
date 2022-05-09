package tank.demo16.strategy;


import tank.demo16.Bullet;
import tank.demo16.Tank;

/**
 * @author zhangling  2021/12/9 22:40
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tank.getTf().getBulletList().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf()));
    }
}
