package com.ling.facade;

import com.ling.cor.CollectorChain;
import com.ling.mediator.GameObject;
import com.ling.strategy.FireStrategy;
import com.ling.strategy.FourDirFireStrategy;
import com.ling.tank.*;
import com.ling.util.PropertyMgr;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangling
 * @date 2021/12/10 3:16 下午
 */
@Getter
@Setter
public class GameModel {

    public static final GameModel INSTANT = new GameModel();
    private List<GameObject> objects = new ArrayList<>();
    private Tank myTank;
    CollectorChain chain = new CollectorChain();


    static {
        INSTANT.init();
    }


    private void init() {
        myTank = new Tank(600, 400, Dir.UP, Group.GOOD, this);
        objects.add(myTank);
        // 初始化敌方坦克
        Integer initTankCount = Integer.valueOf((String) PropertyMgr.get("initTankCount"));
        for (Integer i = 0; i < initTankCount; i++) {
            objects.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    /**
     * 画出一个图形
     *
     * @param g
     */
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString("子弹:" + objects.size(), 30, 100);
//        g.drawString("敌方坦克:" + badTanks.size(), 30, 130);
//        g.drawString("爆炸:" + explodes.size(), 30, 150);
        if (myTank != null) {
            myTank.paint(g);
        }
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }
        // 循环遍历子弹和敌方坦克，如果碰撞，两个都移除
//        for (int i = 0; i < bullets.size(); i++) {
//            // 敌方子弹和我方坦克碰撞
//            if (bullets.get(i).getGroup() == Group.BAD) {
//                bullets.get(i).collideWith(myTank);
//            } else {
//                for (int j = 0; j < badTanks.size(); j++) {
//                    bullets.get(i).collideWith(badTanks.get(j));
//                }
//            }
//        }
    }
}
