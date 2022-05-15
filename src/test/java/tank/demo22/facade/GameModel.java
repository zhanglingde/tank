package tank.demo22.facade;


import lombok.Getter;
import lombok.Setter;
import tank.demo22.Dir;
import tank.demo22.Group;
import tank.demo22.cor.CollectorChain;
import tank.demo22.mediator.GameObject;
import tank.demo22.strategy.FireStrategy;
import tank.demo22.strategy.FourDirFireStrategy;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Getter
@Setter
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    private Tank myTank = new Tank(600, 400, Dir.UP, Group.GOOD);
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

    public GameModel() {
        objects.add(myTank);
        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD));
        }

        add(new Wall(0,500));
        add(new Wall(60,500));
        add(new Wall(120,500));
        add(new Wall(180,500));
        add(new Wall(240,500));
        add(new Wall(300,500));
        add(new Wall(360,500));
        add(new Wall(420,500));
        add(new Wall(480,500));

        add(new Wall(410, 736));
        add(new Home(470, 736));
        add(new Wall(530, 736));
        add(new Wall(410, 676));
        add(new Wall(470, 676));
        add(new Wall(530, 676));

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

    public void save(){
        System.out.println("save");
        File file = new File("D:/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(){
        System.out.println("load");
        File file = new File("D:/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank) ois.readObject();
            objects = (List<GameObject>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
