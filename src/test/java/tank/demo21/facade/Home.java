package tank.demo21.facade;

import tank.demo21.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

public class Home extends GameObject {

    int width = ResourceMgr.home.getWidth();
    int height = ResourceMgr.home.getHeight();
    private Rectangle rect;

    public Home(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        g.drawImage(ResourceMgr.home, x, y,null);
    }
}
