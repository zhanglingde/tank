package com.ling.tank;

import com.ling.util.ResourceMgr;
import lombok.*;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * 坦克实体
 *
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Getter
@Setter
public class Tank {

    // 初始位置
    private int x = 200;
    private int y = 200;
    public static int WIDTH = ResourceMgr.tankU.getWidth();
    public static int HEIGHT = ResourceMgr.tankU.getHeight();
    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    /**
     * 维持一个 TankFrame 的引用，tank 需要在 TankFrame 中创建一个子弹
     */
    private TankFrame tf;
    private boolean moving = false;
    private boolean live = true;
    private static final int SPEED = 10;


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
        // g.setColor(Color.YELLOW);
        // g.fillRect(x, y, WIDTH, HEIGHT);
        if (!live) {
            tf.getBadTanks().remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
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
        int bX = x + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.getBullets().add(new Bullet(bX, bY, dir, tf));
    }

    public void die() {
        this.live = false;
    }
}
