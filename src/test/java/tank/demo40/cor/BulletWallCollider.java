package tank.demo40.cor;


import tank.demo40.facade.Bullet;
import tank.demo40.facade.Explode2;
import tank.demo40.facade.GameModel;
import tank.demo40.facade.Wall;
import tank.demo40.mediator.GameObject;

/**
 * 子弹墙碰撞器
 *
 * @author zhangling  2021/12/11 10:35
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (bullet.getRect().intersects(wall.getRect())) {
                bullet.die();
                GameModel.getInstance().remove(wall);
                int eX = wall.getX() + wall.getWidth() / 2 - Explode2.WIDTH / 2;
                int eY = wall.getY() + wall.getHeight() / 2 - Explode2.HEIGHT / 2;
                GameModel.getInstance().add(new Explode2(eX,eY));
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
