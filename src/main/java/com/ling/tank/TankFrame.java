package com.ling.tank;

import com.ling.facade.GameModel;
import com.ling.facade.Tank;
import lombok.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

/**
 * 游戏界面类
 *
 * @author zhangling
 * @date 2021/12/6 4:08 下午
 */
// @Data  注解会报错
@Getter
@Setter
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;
//    GameModel gm = new GameModel();


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

    /**
     * 画出一个图形
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        GameModel.getInstant().paint(g);
    }

    Image offScreenImage = null;
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
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
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
                    GameModel.getInstant().getMyTank().handleFireKey();
                    break;
                case KeyEvent.VK_S:
                    GameModel.getInstant().save();
                    break;
                case KeyEvent.VK_L:
                    GameModel.getInstant().load();
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



            }
            setTankDir();
        }

        /**
         * 设置坦克方向
         */
        private void setTankDir() {
            Tank myTank = GameModel.getInstant().getMyTank();
            // 只要有一个方向就移动
            if (!(bL || bU || bD || bR)) {
                myTank.setMoving(false);
            } else {
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
            }
        }
    }


}
