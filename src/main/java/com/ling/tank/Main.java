package com.ling.tank;

/**
 *
 * @author zhangling
 * @date 2021/12/6 4:16 下午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        // 初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            tankFrame.getBadTanks().add(new Tank(50 + i * 80, 200, Dir.DOWN,Group.BAD, tankFrame));
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
