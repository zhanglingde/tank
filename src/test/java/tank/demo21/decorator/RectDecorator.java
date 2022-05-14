package tank.demo21.decorator;



import tank.demo21.mediator.GameObject;

import java.awt.*;

/**
 * 矩形包装
 *
 * @author zhangling  2021/12/11 16:20
 */
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        // 先画出子弹
        super.paint(g);
        // 画出矩形 super.go 是 TailDecorator 对象
        g.drawRect(super.go.getX(), super.go.getY(), super.go.getWidth() + 2, super.go.getHeight() + 2);

    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}