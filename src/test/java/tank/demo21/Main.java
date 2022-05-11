package tank.demo21;

/**
 * 入口
 * @author zhangling
 * @date 2021/12/6 4:16 下午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        new Thread(()-> new Audio("audio/game_start.wav").play()).start();



        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }

    }
}

