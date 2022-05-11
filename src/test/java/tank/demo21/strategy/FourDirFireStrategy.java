package tank.demo21.strategy;


import tank.demo21.Dir;
import tank.demo21.facade.Bullet;
import tank.demo21.facade.GameModel;
import tank.demo21.facade.Tank;

/**
 * @author zhangling  2021/12/9 22:49
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        System.out.println("myTank fire");
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = tank.getY() + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            // tank.getGm().add(new Bullet(bX, bY, dir, tank.getGroup(), tank.getGm()));
            GameModel.getInstance().add(new Bullet(bX, bY, dir, tank.getGroup()));
        }
    }
}
