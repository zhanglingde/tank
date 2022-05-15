package tank.demo22.decorator;



import tank.demo22.mediator.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.x = go.getX();
        this.y = go.getY();
        // g.drawLine(x, y, x + super.go.getHeight(), y + super.go.getWidth());


        // 实时获取子弹的位置，将子弹位置赋值给 Tail 的位置，以便 RectDecorator 获取使用
        g.setColor(Color.YELLOW);
        // 起点-终点
        g.drawLine(super.go.getX(), super.go.getY(), super.go.getX() + super.go.getHeight(), super.go.getY() + super.go.getWidth());
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }
}
