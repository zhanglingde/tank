import tank.demo05.Tank;

import java.awt.*;

public class Readme{

    /**
     * 1. 画出一个界面 {@link tank.demo01.Tank}
     * 2. 添加键盘监听 {@link tank.demo01.TankFrame}
     * 3. 实现方块的方向移动 {@link tank.demo01.T1}
     *
     */
    void demo01() {}

    /**
     * 1. 优化方块移动，给方块加上方向和速度属性，操作时修改属性的值（面向对象）
     *
     * {@link tank.demo02.T2}
     * {@link tank.demo02.TankFrame}
     */
    void demo02() {}

    /**
     * 1. 抽象出坦克类，并封装相应的属性和方法  {@link tank.demo03.Tank}
     *
     */
    void demo03(){}

    /**
     * 1. 坦克添加移动属性，在没有按方向时停止移动  {@link tank.demo04.TankFrame}
     * 2. 添加子弹类，并在界面上绘制出子弹  {@link tank.demo04.Bullet}
     */
    void demo04(){}

    /**
     * 1. control 控制发射子弹      {@link tank.demo05.Tank#fire()}
     * 2. 修改游戏界面子弹容器为集合  {@link tank.demo05.TankFrame#bulletList}
     */
    void demo05(){}

    /**
     * 1. 解决子弹不断增加存在内存溢出的问题   {@link tank.demo06.Bullet#living}
     */
    void demo06(){}

    /**
     * 1. 读取静态资源文件                              {@link ImageTest}
     * 2. 用图片替换                                     {@link tank.demo07.Bullet#paint(Graphics)}
     * 3. 修改子弹到正确位置，相对于最左上角的相对位置        {@link tank.demo07.Tank#fire()}
     * 4. 添加双缓冲                                     {@link tank.demo07.TankFrame#update(Graphics)}
     */
    void demo07() {}

    /**
     * 初始化地方坦克 {}
     */
    void demo08(){}

    /**
     * 子弹碰到敌方坦克，增加碰撞检测；遍历子弹和敌方坦克集合，如果碰撞，两个都消失   {@link tank.demo09.Bullet#collideWith(tank.demo09.Tank)}
     */
    void demo09(){}

    /**
     * 地方坦克随机开火，加入 Group 区分阵营
     */
    void demo10(){}

    /**
     * 添加爆炸
     */
    void demo11(){}

    /**
     * 在正确位置添加爆炸
     */
    void demo12(){}

    /**
     * 添加声音
     */
    void demo13(){}

    /**
     * 坦克添加边界检测，地方坦克随机移动（Rectangle）
     */
    void demo14(){}

    /**
     * 策略模式
     */
    void demo15(){}

    /**
     * 门面模式（外观模式）：抽象出 GameModel，将 Model 与 View 分离；同时 GameModel 作为 Facade 统一与 Frame 交互，同时负责与内部各个对象的交互
     */
    void demo16(){}

    /**
     * 调停者模式（中介者模式）：将所有物体抽象出一个父类 GameObject，游戏里所有物体与这个 GameObject 关联, GameModel 作为门面与外部进行交互，GameObject 作为中介者与内部各个对象关联
     */
    void demo17(){}

    /**
     * 责任链模式：碰撞检测
     */
    void demo18(){}

    /**
     * 单例模式：{@link GameMo}
     */
    void demo19(){}

    /**
     * 增强墙体：
     */
    void demo20(){}

    /**
     * 装饰者模式
     */
    void demo21(){}


}