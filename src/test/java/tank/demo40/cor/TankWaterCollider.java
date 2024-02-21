package tank.demo40.cor;


import tank.demo40.Group;
import tank.demo40.facade.Tank;
import tank.demo40.facade.Water;
import tank.demo40.mediator.GameObject;

/**
 * 坦克墙碰撞器
 *
 * @author zhangling  2021/12/11 10:43
 */
public class TankWaterCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Water) {
            Tank tank = (Tank) o1;
            Water water = (Water) o2;
            if (tank.getRect().intersects(water.getRect())) {
                if (tank.getGroup() == Group.BAD) {
                    tank.back();
                } else {
                    // Good Tank 碰撞墙进行处理
                    switch (tank.getDir()) {
                        case LEFT:
                            tank.setX(water.getX() + water.getWidth());
                            tank.setY(water.getY());
                            break;
                        case UP:
                            tank.setX(water.getX());
                            tank.setY(water.getY() + water.getHeight());
                            break;
                        case RIGHT:
                            tank.setX(water.getX() - water.getWidth());
                            tank.setY(water.getY());
                            break;
                        case DOWN:
                            tank.setX(water.getX());
                            tank.setY(water.getY() - water.getHeight());
                            break;
                    }

                }
            }
        } else if (o1 instanceof Water && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
