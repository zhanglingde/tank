package tank;

/**
 * @author zhangling
 * @date 2021/12/6 4:16 下午
 */
public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
