package tank.demo40.strategy;


import tank.demo40.facade.Bullet;
import tank.demo40.facade.GameModel;
import tank.demo40.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:40
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        GameModel.getInstance().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup()));
    }
}
