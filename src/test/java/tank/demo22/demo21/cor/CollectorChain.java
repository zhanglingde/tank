package tank.demo22.demo21.cor;




import tank.demo22.demo21.mediator.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链
 *
 * @author zhangling
 * @date 2022/5/10 7:04 下午
 */
public class CollectorChain implements Collider {

    private List<Collider> colliders = new ArrayList<>();

    public CollectorChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new BulletWallCollider());
        add(new TankWallCollider());
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
