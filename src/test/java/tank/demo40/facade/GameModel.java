package tank.demo40.facade;


import lombok.Getter;
import lombok.Setter;
import tank.demo40.Dir;
import tank.demo40.Group;
import tank.demo40.cor.CollectorChain;
import tank.demo40.mediator.GameObject;
import tank.demo40.strategy.DefaultFireStrategy;
import tank.demo40.strategy.FireStrategy;
import util.MapUtil;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    private Tank myTank = new Tank(240, 756, Dir.UP, Group.GOOD, false);
    private List<GameObject> objects = new ArrayList<>();
    CollectorChain chain = new CollectorChain();
    private Map<UUID, GameObject> objectMap = new HashMap<>();


    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
        objectMap.put(gameObject.getId(), gameObject);
    }

    public void remove(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public GameObject getByUUId(UUID uuid) {
        return objectMap.get(uuid);
    }


    FireStrategy fireStrategy = new DefaultFireStrategy();

    static {
        INSTANCE.init();
    }

    private void init() {
        objects.add(myTank);
        // 初始化敌方坦克
        add(new Tank(0, 0, Dir.DOWN, Group.BAD, true));
        add(new Tank(360, 0, Dir.DOWN, Group.BAD, true));
        add(new Tank(720, 0, Dir.DOWN, Group.BAD, true));
        add(new Explode2(100,100));

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
    }
}
