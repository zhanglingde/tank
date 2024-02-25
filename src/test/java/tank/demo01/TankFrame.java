package tank.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;
import tank.demo02.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 画一个移动的图形
 *
 * @author zhangling
 * @date 2021/12/6 4:08 下午
 */
@Data
@AllArgsConstructor
public class  TankFrame extends Frame {

    private int x = 200;
    private int y = 200;


    public TankFrame(){
        setTitle("tank war");
        setSize(1000, 800);
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
        System.out.println("paint...");
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("数量", 30, 100);
        g.fillRect(x, y, 50, 60);
//        x += 10;
//        y += 10;
        g.setColor(color);
    }

    /**
     * 键盘监听处理对象，内部类：因为这个类只有TankFrame使用，所以使用内部类
     */
    class MyKeyListener extends KeyAdapter {

        // 键被按下调用
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e);
            if (e.getKeyCode() == 37) {
                x -= 10;
            }
            if (e.getKeyCode() == 38) {
                y -= 10;
            }
            if (e.getKeyCode() == 39) {
                x += 10;
            }
            if (e.getKeyCode() == 40) {
                y += 10;
            }
        }

        // 键离开时调用
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println("key released");
        }
    }
}
