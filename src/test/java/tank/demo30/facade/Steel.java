package tank.demo30.facade;

import tank.demo30.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

/**
 * 钢墙
 */
public class Steel extends GameObject {

    int width = ResourceMgr.wall.getWidth();
    int height = ResourceMgr.wall.getHeight();
    private Rectangle rect;

    public Steel(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        g.drawImage(ResourceMgr.steel, x, y,null);
        // 画一个矩形
        // g.fillRect(x, y, w, h);
    }
}
