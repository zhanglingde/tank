package tank.demo30.strategy;


import tank.demo30.facade.Bullet;
import tank.demo30.facade.GameModel;
import tank.demo30.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:53
 */
public class TwoBulletFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank){
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        // tank.getGm().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getGm()));

        GameModel.getInstance().add(new Bullet(bX, bY, tank.getDir(), tank.getGroup()));
        // new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf());
    }
}
