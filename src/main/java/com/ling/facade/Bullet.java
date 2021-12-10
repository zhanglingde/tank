package com.ling.facade;

import com.ling.mediator.GameObject;
import com.ling.tank.*;
import com.ling.util.ResourceMgr;
import lombok.Data;

import java.awt.*;

/**
 * @author zhangling
 * @date 2021/12/7 12:55 下午
 */
@Data
public class Bullet extends GameObject {

    private int x, y;
    public static int WIDTH = ResourceMgr.buffetU.getWidth();
    public static int HEIGHT = ResourceMgr.buffetU.getHeight();
    private Dir dir;
    private boolean live = true;
    private Group group;
    private static final int SPEED = 10;
//    private TankFrame tf;
    /**
     * rect可以想象成一个矩形，记录边框位置，做碰撞检测
     */
    private Rectangle rect = new Rectangle();

    /**
     * 实现了Model:Tank,Bullet,Explode等和View：TankFrame的分离，
     */
    private GameModel gm;

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
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

    @Override
    public void paint(Graphics g) {
        // g.setColor(Color.RED);
        // // 绘制一个圆
        // g.fillOval(x, y, WIDTH, HEIGHT);
        if (!live) {
            gm.remove(this);
            return;
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL2, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU2, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR2, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD2, x, y, null);
                break;
            default:
                break;
        }

        move();

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
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            live = false;
        }
    }

    /**
     * 子弹碰撞坦克，
     *
     * @param tank
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) {
            return;
        }
        // todo 用一个 rect 来记录子弹的位置（单例模式）
        // Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        // Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(tank.getRect())) {
            this.die();
            tank.die();
            // 计算爆炸的位置
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gm.add(new Explode(eX, eY, gm));
        }
    }

    private void die() {
        this.live = false;
    }
}
