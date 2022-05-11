package tank.demo20;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tank.demo20.facade.GameModel;
import tank.demo20.facade.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
    public static int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

    // GameModel gm = new GameModel();


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
        // gm.paint(g);
        GameModel.getInstance().paint(g);
    }


    /**
     * 添加双缓冲，解决界面闪烁问题（添加后界面会变黑）
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);

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
                    // gm.getFireStrategy().fire(gm.getMyTank());
                    GameModel.getInstance().getFireStrategy().fire(GameModel.getInstance().getMyTank());
                    // fireStrategy.fire(myTank);
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
            // Tank myTank = gm.getMyTank();
            Tank myTank = GameModel.getInstance().getMyTank();
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
