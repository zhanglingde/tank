package tank.demo30.cor;


import tank.demo30.facade.Bullet;
import tank.demo30.facade.Steel;
import tank.demo30.facade.Wall;
import tank.demo30.mediator.GameObject;

/**
 * 子弹墙碰撞器
 * @author zhangling  2021/12/11 10:35
 */
public class BulletSteelCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Steel) {
            Bullet bullet = (Bullet) o1;
            Steel steel = (Steel) o2;
            if (bullet.getRect().intersects(steel.getRect())) {
                bullet.die();
            }
        } else if (o1 instanceof Steel && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
