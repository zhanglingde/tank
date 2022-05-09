package tank.demo11;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * 游戏界面类
 *
 * @author zhangling
 * @date 2021/12/6 4:08 下午
 */
// @Data  注解会报错
@Getter
@Setter
@AllArgsConstructor
public class TankFrame extends Frame {
    private static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

    Tank myTank = new Tank(200, 400, Dir.UP, Group.GOOD, this);
    // Bullet bullet = new Bullet(300, 300, Dir.DOWN);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> badTanks = new ArrayList<>();
    Explode explode = new Explode(100,100,this);


    {
        for (int i = 0; i < 5; i++) {
            badTanks.add(new Tank(50 + (i * 100), 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public TankFrame() {
        setTitle("tank war");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setVisible(true);
        setResizable(false);

        this.addKeyListener(new MyKeyListener());

        // 添加关闭窗口事件,退出程序
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;



    /**
     * 画出一个图形
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // System.out.println("paint...");
        g.setColor(Color.WHITE);
        g.drawString("子弹数量" + bulletList.size(), 30, 100);
        g.drawString("坦克数量" + badTanks.size(), 30, 120);

        if (myTank != null) {
            myTank.paint(g);
        }
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        for (int i = 0; i < badTanks.size(); i++) {
            badTanks.get(i).paint(g);
        }

        explode.paint(g);

        // 循环遍历子弹和敌方坦克，如果碰撞，两个都移除
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < badTanks.size(); j++) {
                bulletList.get(i).collideWith(badTanks.get(j));
            }
        }
    }


    /**
     * 添加双缓冲，解决界面闪烁问题（添加后界面会变黑）
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);

    }

    /**
     * 键盘监听处理对象，内部类：因为这个类只有TankFrame使用，所以使用内部类
     */
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        // 键被按下调用
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            System.out.println("key = " + key);

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;

            }
            setTankDir();
        }

        // 键离开时调用
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;

            }
            setTankDir();
        }

        /**
         * 设置坦克方向
         */
        private void setTankDir() {
            if (bL || bU || bR || bD) {
                myTank.setMoving(true);

                if (bL) {
                    myTank.setDir(Dir.LEFT);
                }
                if (bU) {
                    myTank.setDir(Dir.UP);
                }
                if (bR) {
                    myTank.setDir(Dir.RIGHT);
                }
                if (bD) {
                    myTank.setDir(Dir.DOWN);
                }
            } else {
                myTank.setMoving(false);
            }
        }
    }


}
