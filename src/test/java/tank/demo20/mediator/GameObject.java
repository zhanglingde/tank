package tank.demo20.mediator;

import java.awt.*;

/**
 * @author zhangling
 * @date 2022/5/10 6:44 下午
 */
public abstract class GameObject {

    protected int x, y;

    /**
     * 绘制图形
     * @param g
     */
    public abstract void paint(Graphics g);
}