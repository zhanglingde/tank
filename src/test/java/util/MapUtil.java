package util;

import tank.demo20.facade.*;

public class MapUtil {

    public static void pass2() {
        // 0 60 120 180 240 300 360 420 480 540 600 660 720
        // 家
        home();
        first();
        second();
        third();
        four();
        five();
        sit();
        seven();
        eight();
        nine();
        ten();
        eleven();
        twelve();
        thirteen();


    }

    private static void home() {
        GameModel.getInstance().add(new Home(360, 730));
        GameModel.getInstance().add(new QuarterWall(330, 730));
        GameModel.getInstance().add(new QuarterWall(330, 760));
        GameModel.getInstance().add(new QuarterWall(330, 700));
        GameModel.getInstance().add(new QuarterWall(360, 700));
        GameModel.getInstance().add(new QuarterWall(390, 700));
        GameModel.getInstance().add(new QuarterWall(420, 700));
        GameModel.getInstance().add(new QuarterWall(420, 730));
        GameModel.getInstance().add(new QuarterWall(420, 760));
    }

    private static void thirteen() {
        GameModel.getInstance().add(new Steel(180, 40));
        GameModel.getInstance().add(new Steel(420, 40));
    }

    private static void twelve() {
        GameModel.getInstance().add(new Wall(60, 100));
        GameModel.getInstance().add(new Steel(180, 100));
        GameModel.getInstance().add(new Wall(420, 100));
        GameModel.getInstance().add(new Wall(540, 100));
        GameModel.getInstance().add(new Wall(660, 100));
    }

    private static void eleven() {
        GameModel.getInstance().add(new Wall(60, 160));
        GameModel.getInstance().add(new Wall(360, 160));  // 7
        GameModel.getInstance().add(new Wall(420, 160));  // 8
        GameModel.getInstance().add(new Wall(540, 160)); // 10
        GameModel.getInstance().add(new Steel(600, 160));
        GameModel.getInstance().add(new Wall(660, 160));
    }

    private static void ten() {
        GameModel.getInstance().add(new Wall(180, 220));
        GameModel.getInstance().add(new Steel(540, 220));
    }

    private static void model() {
        GameModel.getInstance().add(new Grass(0, 280));
        GameModel.getInstance().add(new Wall(60, 280));
        GameModel.getInstance().add(new Steel(120, 280));
        GameModel.getInstance().add(new Wall(180, 280));
        GameModel.getInstance().add(new Grass(240, 280));
        GameModel.getInstance().add(new Wall(300, 280));
        GameModel.getInstance().add(new Steel(360, 280));
        GameModel.getInstance().add(new Steel(420, 280));
        GameModel.getInstance().add(new Steel(480, 280));
        GameModel.getInstance().add(new Steel(540, 280));
        GameModel.getInstance().add(new Steel(600, 280));
        GameModel.getInstance().add(new Steel(660, 280));
        GameModel.getInstance().add(new Steel(720, 280));
    }

    private static void nine() {
        GameModel.getInstance().add(new Grass(0, 280));
        GameModel.getInstance().add(new Wall(240, 280));
        GameModel.getInstance().add(new Steel(360, 280));
        GameModel.getInstance().add(new Wall(540, 280));
        GameModel.getInstance().add(new Grass(600, 280));
        GameModel.getInstance().add(new Wall(660, 280));
        GameModel.getInstance().add(new Steel(720, 280));
    }

    private static void eight() {
        GameModel.getInstance().add(new Grass(0, 340));
        GameModel.getInstance().add(new Grass(60, 340));
        GameModel.getInstance().add(new Wall(300, 340));
        GameModel.getInstance().add(new Wall(480, 340));
        GameModel.getInstance().add(new Grass(600, 340));
    }

    private static void seven() {
        GameModel.getInstance().add(new Wall(60, 400));
        GameModel.getInstance().add(new Wall(120, 400));
        GameModel.getInstance().add(new Wall(180, 400));
        GameModel.getInstance().add(new Grass(240, 400));
        GameModel.getInstance().add(new Grass(300, 400));
        GameModel.getInstance().add(new Grass(360, 400));
        GameModel.getInstance().add(new Steel(420, 400));
        GameModel.getInstance().add(new Grass(600, 400));
        GameModel.getInstance().add(new Wall(660, 400));
    }

    private static void sit() {
        GameModel.getInstance().add(new Steel(180, 460));
        GameModel.getInstance().add(new Grass(240, 460));
        GameModel.getInstance().add(new Wall(300, 460));
        GameModel.getInstance().add(new Wall(420, 460));
        GameModel.getInstance().add(new Wall(540, 460));
        GameModel.getInstance().add(new Wall(660, 460));
    }

    private static void five() {
        GameModel.getInstance().add(new Steel(0, 520));
        GameModel.getInstance().add(new Wall(60, 520));
        GameModel.getInstance().add(new Steel(180, 520));
        GameModel.getInstance().add(new Wall(300, 520));
        GameModel.getInstance().add(new Wall(420, 520));
        GameModel.getInstance().add(new Wall(660, 520));
    }

    private static void four() {
        GameModel.getInstance().add(new Wall(60, 580));
        GameModel.getInstance().add(new Wall(180, 580));
        GameModel.getInstance().add(new Wall(300, 580));
        GameModel.getInstance().add(new Wall(360, 580));
        GameModel.getInstance().add(new Wall(420, 580));
        GameModel.getInstance().add(new Wall(540, 580));
        GameModel.getInstance().add(new Steel(600, 580));
        GameModel.getInstance().add(new Wall(660, 580));
    }

    private static void third() {
        GameModel.getInstance().add(new Wall(60, 610));
        GameModel.getInstance().add(new QuarterWall(180, 640));
        GameModel.getInstance().add(new QuarterWall(210, 640));
        GameModel.getInstance().add(new QuarterWall(300, 640));
        GameModel.getInstance().add(new QuarterWall(330, 640));
        GameModel.getInstance().add(new QuarterWall(360, 640));
        GameModel.getInstance().add(new QuarterWall(390, 640));
        GameModel.getInstance().add(new QuarterWall(420, 640));
        GameModel.getInstance().add(new QuarterWall(450, 640));
    }

    private static void second() {
        GameModel.getInstance().add(new Wall(60, 670));
        GameModel.getInstance().add(new Wall(540, 670));
        GameModel.getInstance().add(new Wall(660, 670));
    }

    private static void first() {
        GameModel.getInstance().add(new Wall(60, 730));
        GameModel.getInstance().add(new Wall(180, 730));
        GameModel.getInstance().add(new Wall(540, 730));
        GameModel.getInstance().add(new Wall(600, 730));
        GameModel.getInstance().add(new Wall(660, 730));
    }
}
