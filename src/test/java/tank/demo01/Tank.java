package tank.demo01;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 画一个图形
 *
 * @author zhangling
 * @date 2021/12/6 4:08 下午
 */
public class Tank {
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setTitle("tank war");
        f.setSize(1000, 800);
        f.setResizable(false);
        f.setVisible(true);

        // 添加关闭窗口事件,退出程序
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
