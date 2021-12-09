
/**
 * 3. 抽象出坦克类，并封装相应的属性 {@link com.ling.tank.Tank}
 * 4. 坦克增加静止属性
 * 5. 添加子弹类，并在界面上绘制出来 {@link com.ling.tank.Bullet}
 * 7. 按 ctrl 发射子弹，可以有多个（子弹使用增强 for 循环会报错） {@link com.ling.tank.Tank#fire()}
 * 8. 解决子弹不断增加存在内存泄漏的问题（超过边框从集合中移除）集合为空时报错
 * 9. 读取图片资源  {@link ImageTest#test()}
 * 10. 将坦克，子弹用图片替换（读取图片，直接画图片），修改子弹到正确位置，相对于最左上角的相对位置
 * 11. 初始化敌方坦克
 * 12. 子弹碰到敌方坦克，增加碰撞检测；遍历子弹和敌方坦克集合，如果碰撞，两个都死   {@link com.ling.tank.Bullet#collideWith(com.ling.tank.Tank)}
 * 13. 加入 Group 区分阵营，敌方坦克随机发射子弹 (test02 分支)          {@link com.ling.tank.TankFrame#paint(java.awt.Graphics)}
 * 14. 加入爆炸        {@link com.ling.tank.Explode}
 * 15. 加入声音（音频文件在资源区无效）
 * 16. 添加图片工具类，通过一张图片转换方向得到多个方向的图片，更换不同阵营坦克图片    {@link com.ling.util.ImageUtil}
 */



/**
 * 分支
 * 1. test1：子弹碰撞敌方坦克闪烁
 * 2. test02：敌方子弹和我方坦克碰撞异常
 */