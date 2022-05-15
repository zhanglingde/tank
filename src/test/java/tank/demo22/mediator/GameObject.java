package tank.demo22.mediator;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

/**
 * @author zhangling
 * @date 2022/5/10 6:44 下午
 */
@Getter
@Setter
public abstract class GameObject implements Serializable {

    protected int x, y;

    /**
     * 绘制图形
     * @param g
     */
    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}