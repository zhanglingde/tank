package tank.demo30.cor;


import tank.demo30.Group;
import tank.demo30.facade.Steel;
import tank.demo30.facade.Tank;
import tank.demo30.mediator.GameObject;

/**
 * 坦克墙碰撞器
 *
 * @author zhangling  2021/12/11 10:43
 */
public class TankSteelCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Steel) {
            Tank tank = (Tank) o1;
            Steel steel = (Steel) o2;
            if (tank.getRect().intersects(steel.getRect())) {
                if (tank.getGroup() == Group.BAD) {
                    tank.back();
                } else {
                    // Good Tank 碰撞墙进行处理
                    switch (tank.getDir()) {
                        case LEFT:
                            tank.setX(steel.getX() + steel.getWidth());
                            tank.setY(steel.getY());
                            break;
                        case UP:
                            tank.setX(steel.getX());
                            tank.setY(steel.getY() + steel.getHeight());
                            break;
                        case RIGHT:
                            tank.setX(steel.getX() - steel.getWidth());
                            tank.setY(steel.getY());
                            break;
                        case DOWN:
                            tank.setX(steel.getX());
                            tank.setY(steel.getY() - steel.getHeight());
                            break;
                    }

                }
            }
        } else if (o1 instanceof Steel && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
