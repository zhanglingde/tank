package tank.demo30.facade;


import lombok.Getter;
import lombok.Setter;
import tank.demo30.Dir;
import tank.demo30.Group;
import tank.demo30.cor.CollectorChain;
import tank.demo30.mediator.GameObject;
import tank.demo30.strategy.FireStrategy;
import tank.demo30.strategy.FourDirFireStrategy;
import util.MapUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    private Tank myTank = new Tank(240, 756, Dir.UP, Group.GOOD);
    private List<GameObject> objects = new ArrayList<>();
    CollectorChain chain = new CollectorChain();


    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    public static GameModel getInstance(){
        return INSTANCE;
    }



    FireStrategy fireStrategy = new FourDirFireStrategy();

    static {
        INSTANCE.init();
    }

    private void init(){
        objects.add(myTank);
        // 初始化敌方坦克
        // for (int i = 0; i < 5; i++) {
        //     // gm.add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD, this));
        //     add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD));
        // }


        MapUtil.pass2();
    }

    public GameModel() {

    }





    /**
     * 画出一个图形
     *
     * @param g
     */
    public void paint(Graphics g) {

        g.setColor(Color.WHITE);

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
