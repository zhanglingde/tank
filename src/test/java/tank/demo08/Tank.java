package tank.demo08;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import util.ResourceMgr;

import java.awt.*;

/**
 * 坦克实体
 *
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tank {

    // 初始位置
    private int x = 200;
    private int y = 200;

    private static int WIDTH = ResourceMgr.goodTankU.getWidth();
    private static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10;
    private boolean moving = false;
    /**
     * 维持一个 TankFrame 的引用，tank 需要在 TankFrame 中创建一个子弹
     */
    private TankFrame tf;


    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;

    }

    /**
     * 绘制坦克
     *
     * @param g
     */
    public void paint(Graphics g) {


        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    /**
     * 坦克移动
     */
    private void move() {
        if (moving) {
            switch (dir) {
                case LEFT:
                    x -= SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
            }
        }
    }

    /**
     * 坦克开火
     */
    public void fire() {
        int bX = x + WIDTH / 2 - Bullet.WIDTH;
        int bY = y + HEIGHT / 2 - Bullet.WIDTH;
        tf.getBulletList().add(new Bullet(bX, bY, dir, tf));
    }
}

