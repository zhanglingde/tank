package tank.demo30.facade;

import lombok.Getter;
import tank.demo30.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

/**
 * 钢墙
 */
@Getter
public class Steel extends GameObject {

    int width = ResourceMgr.wall.getWidth();
    int height = ResourceMgr.wall.getHeight();
    public Rectangle rect;

    public Steel(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.steel, x, y, null);
    }
}
