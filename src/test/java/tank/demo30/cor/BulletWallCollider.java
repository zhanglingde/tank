package tank.demo30.cor;


import tank.demo30.facade.*;
import tank.demo30.mediator.GameObject;

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
                int eX = wall.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = wall.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode2(eX,eY));
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
