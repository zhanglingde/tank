package com.ling.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/7 12:55 下午
 */
@Data
public class Bullet {

    private int x, y;
    private int WIDTH = 30;
    private int HEIGHT = 30;
    private Dir dir;
    private boolean live = true;
    private static final int SPEED = 10;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        // 绘制一个圆
        g.fillOval(x, y, WIDTH, HEIGHT);

        move();
        if (!live) {
            tf.getBullets().remove(this);
        }
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
}
