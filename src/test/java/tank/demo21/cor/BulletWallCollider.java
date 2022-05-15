package tank.demo21.cor;


import tank.demo21.cor.Collider;
import tank.demo21.facade.Bullet;
import tank.demo21.facade.Wall;
import tank.demo21.mediator.GameObject;

/**
 * 子弹墙碰撞器
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
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}