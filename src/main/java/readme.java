
/**
 * 3. 抽象出坦克类，并封装相应的属性 {@link com.ling.facade.Tank}
 * 4. 坦克增加静止属性
 * 5. 添加子弹类，并在界面上绘制出来 {@link com.ling.facade.Bullet}
 * 7. 按 ctrl 发射子弹，可以有多个（子弹使用增强 for 循环会报错） {@link com.ling.facade.Tank#fire()}
 * 8. 解决子弹不断增加存在内存泄漏的问题（超过边框从集合中移除）集合为空时报错
 * 9. 读取图片资源  {@link ImageTest#test()}
 * 10. 将坦克，子弹用图片替换（读取图片，直接画图片），修改子弹到正确位置，相对于最左上角的相对位置
 * 11. 初始化敌方坦克
 * 12. 子弹碰到敌方坦克，增加碰撞检测；遍历子弹和敌方坦克集合，如果碰撞，两个都死   {@link com.ling.facade.Bullet#collideWith(Tank)}
 * 13. 加入 Group 区分阵营，敌方坦克随机发射子弹 (test02 分支)          {@link com.ling.tank.TankFrame#paint(java.awt.Graphics)}
 * 14. 加入爆炸        {@link com.ling.facade.Explode}
 * 15. 加入声音（音频文件在资源区无效）
 * 16. 添加图片工具类，通过一张图片转换方向得到多个方向的图片，更换不同阵营坦克图片    {@link com.ling.util.ImageUtil}
 * 17. 坦克增加边界检测，敌方坦克随机移动            {@link com.ling.facade.Tank#boundsCheck()}
 * 18. 修复碰撞检测时产生多个 rect问题，在子弹和坦克中都维护一个 rect，随着子弹和坦克的移动更新 rect
 * 19. 添加配置文件，移除敌方坦克初始化，为网络版作准备
 * 20. 策略模式
 * 21. 门面模式：抽象出 GameModel，将 Model 与 View 分离；同时 GameModel 作为 Facade 统一与 Frame 打交道，同时负责与内部各个对象的交互
 * 22. 调停者模式（中介者模式）：将所有物体抽象出一个父类 GameObject，游戏里所有物体与这个 GameObject 关联, GameModel 作为门面与外部进行交互，GameObject 作为中介者与内部各个对象关联
 *     单例模式：将 GameModel 设置为单例
 * 23. 责任链模式：碰撞器使用责任链模式，子弹-坦克碰撞，坦克-坦克碰撞，坦克-墙碰撞
 */

/**
 * 设计模式：
 * 1. 策略模式 {@link com.ling.strategy.FireStrategy#fire(Tank)}
 */



/**
 * 分支
 * 1. test1：子弹碰撞敌方坦克闪烁
 * 2. test02：敌方子弹和我方坦克碰撞异常
 * net:网络版
 * dp：设计模式版
 *      策略模式
 *      抽象工厂模式-不同的坦克、子弹、爆炸效果等
 * dp-gamemodel
 *
 */

import com.ling.facade.Tank;