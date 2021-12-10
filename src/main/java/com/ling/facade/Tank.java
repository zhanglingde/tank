package com.ling.facade;

import com.ling.mediator.GameObject;
import com.ling.tank.Dir;
import com.ling.tank.Group;
import com.ling.tank.TankFrame;
import com.ling.util.ResourceMgr;
import lombok.*;

import java.awt.*;
import java.util.Random;

/**
 * 坦克实体
 *
 * @author zhangling
 * @date 2021/12/7 11:22 上午
 */
@Getter
@Setter
public class Tank extends GameObject {

    // 初始位置
    private int x = 200;
    private int y = 200;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    /**
     * 设置默认方向向下
     */
    private Dir dir = Dir.DOWN;
    /**
     * 维持一个 TankFrame 的引用，tank 需要在 TankFrame 中创建一个子弹
     */
    private TankFrame tf;
    /**
     * 实现了Model:Tank,Bullet,Explode等和View：TankFrame的分离，
     */
    private GameModel gm;
    private boolean moving = false;
    private boolean live = true;
    private Group group;
    Random random = new Random();
    private static final int SPEED = 5;
    private Rectangle rect = new Rectangle();


    public Tank(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    /**
     * 绘制坦克
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // g.setColor(Color.YELLOW);
        // g.fillRect(x, y, WIDTH, HEIGHT);


        if (!live) {
            if (group == Group.BAD) {
                GameModel.INSTANT.remove(this);
            } else {
//                gm.setMyTank(null);
            }

            return;
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

        if (group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
        }
        //
        // if (group == Group.BAD && random.nextInt(100) > 95) {
        //     randomDir();
        // }

        // 遍界检测
        boundsCheck();
    }

    private void boundsCheck() {
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

    private void randomDir() {
        // 从方向枚举数组的4个方向中，随机取一个方向的下标
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 坦克开火
     */
    public void fire() {
        int bX = x + Tank.WIDTH / 2 - Bullet.HEIGHT / 2;
        int bY = y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        gm.add(new Bullet(bX, bY, dir, this.group, gm));
    }

    public void die() {
        this.live = false;
    }
}
