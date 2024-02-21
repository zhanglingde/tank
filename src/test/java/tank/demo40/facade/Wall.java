package tank.demo40.facade;

import lombok.Getter;
import lombok.Setter;
import tank.demo40.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

@Getter
@Setter
public class Wall extends GameObject {

    public int width = ResourceMgr.wall.getWidth();
    public int height = ResourceMgr.wall.getHeight();
    public Rectangle rect;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;

        this.rect = new Rectangle(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.wall, x, y, null);
    }
}
