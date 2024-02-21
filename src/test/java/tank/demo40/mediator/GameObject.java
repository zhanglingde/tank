package tank.demo40.mediator;

import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author zhangling
 * @date 2022/5/10 6:44 下午
 */
@Getter
public abstract class GameObject implements Serializable {

    protected int x, y;

    protected UUID id = UUID.randomUUID();

    /**
     * 绘制图形
     * @param g
     */
    public abstract void paint(Graphics g);
}