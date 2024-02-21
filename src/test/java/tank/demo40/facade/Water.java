package tank.demo40.facade;

import lombok.Getter;
import lombok.Setter;
import tank.demo40.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

@Getter
@Setter
public class Water extends GameObject {

    int width = ResourceMgr.water.getWidth();
    int height = ResourceMgr.water.getHeight();
    public Rectangle rect;

    public Water(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.water, x, y, null);
    }
}
