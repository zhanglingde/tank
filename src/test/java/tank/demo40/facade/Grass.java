package tank.demo40.facade;

import tank.demo40.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

public class Grass extends GameObject {

    int width = ResourceMgr.grass.getWidth();
    int height = ResourceMgr.grass.getHeight();
    private Rectangle rect;

    public Grass(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);

        g.drawImage(ResourceMgr.grass, x, y,null);

    }
}
