package com.ling.facade;

import com.ling.mediator.GameObject;
import com.ling.util.ResourceMgr;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * @author zhangling  2021/12/11 10:25
 */
@Getter
@Setter
public class Wall extends GameObject {

    int width = ResourceMgr.wall.getWidth();
    int height = ResourceMgr.wall.getHeight();
    private Rectangle rect;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        this.rect = new Rectangle(x, y,width,height );
    }

    @Override
    public void paint(Graphics g) {
//        g.setColor(Color.DARK_GRAY);
        // 画一个矩形
//        g.fillRect(x, y, width, height);
        g.drawImage(ResourceMgr.wall, x, y, null);
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
