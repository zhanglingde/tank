package com.ling.tank;

import com.ling.util.ResourceMgr;
import lombok.*;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.Random;

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
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
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
    private Group group;
    Random random = new Random();
    private static final int SPEED = 10;


    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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
            if (group == Group.BAD) {
                tf.getBadTanks().remove(this);
            } else {
                tf.setMyTank(null);
            }

            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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

        if (group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
    }

    /**
     * 坦克开火
     */
    public void fire() {
        int bX = x + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.getBullets().add(new Bullet(bX, bY, dir, this.group, tf));
    }

    public void die() {
        this.live = false;
    }
}
