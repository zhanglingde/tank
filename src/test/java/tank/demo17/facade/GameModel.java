package tank.demo17.facade;


import lombok.Getter;
import lombok.Setter;
import tank.demo17.Dir;
import tank.demo17.Group;
import tank.demo17.mediator.GameObject;
import tank.demo17.strategy.FireStrategy;
import tank.demo17.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class GameModel {

    private Tank myTank = new Tank(600, 400, Dir.UP, Group.GOOD, this);
    private List<GameObject> objects = new ArrayList<>();

    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    // private List<Bullet> bulletList = new ArrayList<Bullet>();
    // private List<Tank> badTanks = new ArrayList<>();
    // private List<Explode> explodes = new ArrayList<>();

    FireStrategy fireStrategy = new FourDirFireStrategy();

    public GameModel() {
        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            // gm.add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD, this));
            add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD, this));
        }
    }

    /**
     * 画出一个图形
     *
     * @param g
     */
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        // g.drawString("子弹:" + objects.size(), 30, 100);
        // g.drawString("敌方坦克:" + badTanks.size(), 30, 130);
        // g.drawString("爆炸:" + explodes.size(), 30, 150);
        if (myTank != null) {
            myTank.paint(g);
        }
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // for (int i = 0; i < bulletList.size(); i++) {   // 使用增强 for 循环会报错
        //     bulletList.get(i).paint(g);
        // }
        // for (int i = 0; i < badTanks.size(); i++) {
        //     badTanks.get(i).paint(g);
        // }
        // for (int i = 0; i < explodes.size(); i++) {
        //     explodes.get(i).paint(g);
        // }


        // 循环遍历子弹和敌方坦克，如果碰撞，两个都移除
        // for (int i = 0; i < bulletList.size(); i++) {
        //     // 敌方子弹和我方坦克碰撞
        //     if (bulletList.get(i).getGroup() == Group.BAD) {
        //         bulletList.get(i).collideWith(myTank);
        //     } else {
        //         for (int j = 0; j < badTanks.size(); j++) {
        //             bulletList.get(i).collideWith(badTanks.get(j));
        //         }
        //     }
        // }

    }
}
