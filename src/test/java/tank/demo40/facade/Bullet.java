package tank.demo40.facade;

import lombok.Data;
import lombok.NoArgsConstructor;
import tank.demo40.Dir;
import tank.demo40.Group;
import tank.demo40.TankFrame;
import tank.demo40.mediator.GameObject;
import tank.demo40.net.message.BulletNewMsg;
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
    // private GameModel gm;
    private Group group;

    private Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        // this.gm = gm;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public Bullet(BulletNewMsg bulletJoinMsg) {
        this.x = bulletJoinMsg.getX();
        this.y = bulletJoinMsg.getY();
        this.dir = bulletJoinMsg.getDir();
    }

    /**
     * 绘制子弹
     *
     * @param g
     */
    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
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
            // gm.remove(this);
            GameModel.getInstance().remove(this);
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
    public boolean collideWith(Tank tank) {
        // 同一阵营不进行碰撞检测
        if (this.group == tank.getGroup()) {
            return false;
        }

        // 确定此 Rectangle 是否与指定的 Rectangle 相交
        if (rect.intersects(tank.getRect())) {
            this.die();
            tank.die();
            // 计算爆炸的位置
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            GameModel.getInstance().add(new Explode(eX,eY));
            return true;
        }
        return false;
    }

    public void die() {
        this.living = false;
    }
}
