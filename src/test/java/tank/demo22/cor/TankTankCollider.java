package tank.demo22.cor;


import tank.demo22.Group;
import tank.demo22.facade.Tank;
import tank.demo22.mediator.GameObject;

/**
 * @author zhangling
 * @date 2022/5/10 7:05 下午
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getRect().intersects(t2.getRect())) {
                if (t1.getGroup() == t2.getGroup()) {
                    t1.randomDir();
                    t2.randomDir();
                }else{
                    if (t1.getGroup() == Group.BAD) {
                        t1.randomDir();
                    }else {
                        t2.randomDir();
                    }
                }
            }
        }
        return true;
    }
}
