package tank.demo30.cor;


import tank.demo30.Group;
import tank.demo30.facade.Tank;
import tank.demo30.facade.Wall;
import tank.demo30.mediator.GameObject;

/**
 * 坦克墙碰撞器
 *
 * @author zhangling  2021/12/11 10:43
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRect().intersects(wall.getRect())) {
                if (tank.getGroup() == Group.BAD) {
                    tank.back();
                } else {
                    // Good Tank 碰撞墙进行处理
                    switch (tank.getDir()) {
                        case LEFT:
                            tank.setX(wall.getX() + wall.getWidth());
                            tank.setY(wall.getY());
                            break;
                        case UP:
                            tank.setX(wall.getX());
                            tank.setY(wall.getY() + wall.getHeight());
                            break;
                        case RIGHT:
                            tank.setX(wall.getX() - wall.getWidth());
                            tank.setY(wall.getY());
                            break;
                        case DOWN:
                            tank.setX(wall.getX());
                            tank.setY(wall.getY() - wall.getHeight());
                            break;
                    }

                }
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            return collide(o2, o1);
        }
        return true;
    }
}
