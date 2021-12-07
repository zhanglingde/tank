package com.ling.tank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * 坦克实体
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tank {

    // 初始位置
    private int x = 200;
    private int y = 200;
    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 10;

    /**
     * 绘制坦克
     * @param g
     */
    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 60);

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
