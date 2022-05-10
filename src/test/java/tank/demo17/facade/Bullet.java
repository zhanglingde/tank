package tank.demo17.facade;

import lombok.Data;
import lombok.NoArgsConstructor;
import tank.demo17.Dir;
import tank.demo17.Group;
import tank.demo17.TankFrame;
import tank.demo17.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/7 12:55 下午
 */
@Data
@NoArgsConstructor
public class Bullet extends GameObject {

    private int x, y;
    public static int WIDTH = ResourceMgr.bulletU.getWidth();
    public static int HEIGHT = ResourceMgr.bulletU.getHeight();
    private Dir dir;
    private static final int SPEED = 10;
    /**
     * 子弹是否存活
     */
    private boolean living = true;
    private GameModel gm;
    private Group group;

    private Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 绘制子弹
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!living) {
            gm.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }

        move();

        if (!living) {
            // gm.getBulletList().remove(this);
            gm.remove(this);
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
        rect.x = x;
        rect.y = y;
        // 子弹超出边界移除
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    /**
     * 子弹碰撞坦克，
     * @param tank
     */
    public void collideWith(Tank tank) {
        // 同一阵营不进行碰撞检测
        if (this.group == tank.getGroup()) {
            return;
        }

        // Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        // Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        // 确定此 Rectangle 是否与指定的 Rectangle 相交
        if (rect.intersects(tank.getRect())) {
            this.die();
            tank.die();
            // 计算爆炸的位置
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            // gm.getExplodes().add(new Explode(eX,eY,gm));
            gm.add(new Explode(eX,eY,gm));
        }
    }

    private void die() {
        this.living = false;
    }
}
