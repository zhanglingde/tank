package tank.demo19.demo18;

/**
 * 入口
 * @author zhangling
 * @date 2021/12/6 4:16 下午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}

