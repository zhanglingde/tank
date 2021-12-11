package com.ling.facade;

import com.ling.mediator.GameObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author zhangling  2021/12/11 10:25
 */
@Getter
@Setter
public class Wall extends GameObject {

    int width, height;
    private Rectangle rect;

    public Wall(int x,int y,int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        // 画一个矩形
        g.fillRect(x, y, width, height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
