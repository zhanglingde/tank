package com.ling.facade;

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

    private Tank myTank = new Tank(600, 400, Dir.UP, Group.GOOD, this);
    private List<Bullet> bullets = new ArrayList<Bullet>();
    private List<Tank> badTanks = new ArrayList<>();
    // private Explode explode = new Explode(100, 100, this);
    private List<Explode> explodes = new ArrayList<>();

    FireStrategy fireStrategy = new FourDirFireStrategy();

    public GameModel() {
        // 初始化敌方坦克
        Integer initTankCount = Integer.valueOf((String) PropertyMgr.get("initTankCount"));
        for (Integer i = 0; i < initTankCount; i++) {
            badTanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    /**
     * 画出一个图形
     *
     * @param g
     */
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString("子弹:" + bullets.size(), 30, 100);
        g.drawString("敌方坦克:" + badTanks.size(), 30, 130);
        g.drawString("爆炸:" + explodes.size(), 30, 150);
        if (myTank != null) {
            myTank.paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {   // 使用增强 for 循环会报错
            bullets.get(i).paint(g);
        }
        for (int i = 0; i < badTanks.size(); i++) {
            badTanks.get(i).paint(g);
        }
        // 循环遍历子弹和敌方坦克，如果碰撞，两个都移除
        for (int i = 0; i < bullets.size(); i++) {
            // 敌方子弹和我方坦克碰撞
            if (bullets.get(i).getGroup() == Group.BAD) {
                bullets.get(i).collideWith(myTank);
            } else {
                for (int j = 0; j < badTanks.size(); j++) {
                    bullets.get(i).collideWith(badTanks.get(j));
                }
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}
