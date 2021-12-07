package com.ling.tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * 坦克实体
 *
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Data
public class Tank {

    // 初始位置
    private int x = 200;
    private int y = 200;
    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    /**
     * 维持一个 TankFrame 的引用，tank 需要在 TankFrame 中创建一个子弹
     */
    private TankFrame tf;
    private boolean moving = false;
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
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 60);
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
        System.out.println("fire");
        tf.getBullets().add(new Bullet(this.x, this.y, dir, tf));
    }
}
