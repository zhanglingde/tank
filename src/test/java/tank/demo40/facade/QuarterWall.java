package tank.demo40.facade;

import tank.demo40.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

public class QuarterWall extends GameObject {

    int width = ResourceMgr.quarterWall.getWidth();
    int height = ResourceMgr.quarterWall.getHeight();
    private Rectangle rect;

    public QuarterWall(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        g.drawImage(ResourceMgr.quarterWall, x, y,null);
        // 画一个矩形
        // g.fillRect(x, y, w, h);
    }
}
