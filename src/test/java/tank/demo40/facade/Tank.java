package tank.demo40.facade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tank.demo40.Dir;
import tank.demo40.Group;
import tank.demo40.TankFrame;
import tank.demo40.mediator.GameObject;
import tank.demo40.net.message.TankJoinMsg;
import util.ResourceMgr;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * 坦克实体
 *
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tank extends GameObject {

    // 初始位置
    // private UUID id;
    private int x = 200;
    private int y = 200;
    int oldX;
    int oldY;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;
    /**
     * 维持一个 TankFrame 的引用，tank 需要在 TankFrame 中创建一个子弹
     */
    // private GameModel gm;
    private boolean living = true;
    private Group group;
    private Random random = new Random();

    private Rectangle rect = new Rectangle();


    public Tank(int x, int y, Dir dir, Group group, boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    public Tank(TankJoinMsg msg) {
        this.x = msg.getX();
        this.y = msg.getY();
        this.dir = msg.getDir();
        this.group = msg.getGroup();
        this.id = msg.getId();
        this.moving = msg.isMoving();

    }

    /**
     * 绘制坦克
     *
     * @param g
     */
    public void paint(Graphics g) {
        // 坦克死亡移除
        if (!living) {
            if (group == Group.BAD) {
                GameModel.getInstance().remove(this);
            } else {
                GameModel.getInstance().setMyTank(null);
            }
        }

        switch (dir) {
            case LEFT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    /**
     * 坦克移动
     */
    private void move() {
        oldX = x;
        oldY = y;
        if (moving) {
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
        }
        if (group == Group.BAD && random.nextInt(10) > 8) {
            this.fire();
        }
        if (group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }

        // 边界检测
        boundsCheck();
    }

    /**
     * 坦克开火
     */
    public void fire() {
        int bX = x + WIDTH / 2 - Bullet.WIDTH;
        int bY = y + HEIGHT / 2 - Bullet.WIDTH;

        GameModel.getInstance().add(new Bullet(bX, bY, dir, group));
    }

    public void die() {
        this.living = false;
    }

    public void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (y < 30) {
            // 菜单栏高度为 30
            y = 30;
        }
        if (y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }
    }

    public void colliderCheck(){
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
    }

    public void randomDir() {
        // 从方向枚举数组的4个方向中，随机取一个方向的下标
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void back() {
        x = oldX;
        y = oldY;
    }
}
