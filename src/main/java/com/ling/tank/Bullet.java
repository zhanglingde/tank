package com.ling.tank;

import com.ling.util.ResourceMgr;
import lombok.Data;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/7 12:55 下午
 */
@Data
public class Bullet {

    private int x, y;
    public static int WIDTH = ResourceMgr.buffetU.getWidth();
    public static int HEIGHT = ResourceMgr.buffetU.getHeight();
    private Dir dir;
    private boolean live = true;
    private Group group;
    private static final int SPEED = 10;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        // g.setColor(Color.RED);
        // // 绘制一个圆
        // g.fillOval(x, y, WIDTH, HEIGHT);
        if (!live) {
            tf.getBullets().remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.buffetL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.buffetU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.buffetR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.buffetD, x, y, null);
                break;
            default:
                break;
        }

        move();

    }

    private void move() {
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
        if (x < 0 || x > tf.getWidth() || y < 0 || y > tf.getHeight()) {
            live = false;
        }
    }

    /**
     * 子弹碰撞坦克，
     * @param tank
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        // todo 用一个 rect 来记录子弹的位置（单例模式）
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect1.intersects(rect2)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
