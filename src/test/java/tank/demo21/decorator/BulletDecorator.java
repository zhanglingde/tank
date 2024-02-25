package tank.demo21.decorator;


import tank.demo21.facade.Bullet;
import tank.demo21.facade.GameModel;
import tank.demo21.mediator.GameObject;
import util.ResourceMgr;

import java.awt.*;

/**
 * 矩形包装
 *
 * @author zhangling  2021/12/11 16:20
 */
public class BulletDecorator extends GODecorator {

    public static int HEIGHT = ResourceMgr.bulletU2.getHeight();

    public BulletDecorator(GameObject go) {
        super(go);

    }

    @Override
    public void paint(Graphics g) {
        // 先画出子弹
        // super.paint(g);
        // 画出矩形 super.go 是 TailDecorator 对象
        // g.drawRect(super.go.getX(), super.go.getY(), super.go.getWidth() + 2, super.go.getHeight() + 2);

        Bullet bullet = (Bullet) super.go;

        if (!bullet.isLiving()) {
            GameModel.getInstance().remove(this);
            return;
        }

        switch (bullet.getDir()) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL2, super.go.getX() + Bullet.HEIGHT / 2 - HEIGHT / 2, super.go.getY() + Bullet.HEIGHT / 2 - HEIGHT / 2-4, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU2, super.go.getX() + Bullet.HEIGHT / 2 - HEIGHT / 2, super.go.getY() + Bullet.HEIGHT / 2 - HEIGHT / 2, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR2, super.go.getX() + Bullet.HEIGHT / 2 - HEIGHT / 2, super.go.getY() + Bullet.HEIGHT / 2 - HEIGHT / 2-4, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD2, super.go.getX() + Bullet.HEIGHT / 2 - HEIGHT / 2, super.go.getY() + Bullet.HEIGHT / 2 - HEIGHT / 2, null);
                break;
            default:
                break;
        }

        bullet.move();


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