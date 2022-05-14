package tank.demo20.strategy;


import tank.demo20.Dir;
import tank.demo20.facade.Bullet;
import tank.demo20.facade.GameModel;
import tank.demo20.facade.Tank;

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
            GameModel.getInstance().add(new Bullet(bX, bY, dir, tank.getGroup()));
        }
    }
}
