package tank.demo06;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/7 12:55 下午
 */
@Data
@NoArgsConstructor
public class Bullet {

    private int x, y;
    private int WIDTH = 30;
    private int HEIGHT = 30;
    private Dir dir;
    private static final int SPEED = 3;
    /**
     * 子弹是否存活
     */
    private boolean living = true;
    private TankFrame tf;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    /**
     * 绘制子弹
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        // 绘制一个圆
        g.fillOval(x, y, WIDTH, HEIGHT);
        move();

        if (!living) {
            tf.getBulletList().remove(this);
        }
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        // 子弹超出边界移除
        if (x < 0 || x > tf.getWidth() || y < 0 || y > tf.getHeight()) {
            living = false;
        }
    }
}
