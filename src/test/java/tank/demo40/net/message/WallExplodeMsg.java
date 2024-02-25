package tank.demo40.net.message;

import lombok.Getter;
import lombok.Setter;
import tank.demo40.facade.GameModel;
import tank.demo40.facade.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @author zhangling  2021/12/12 21:38
 */
@Getter
@Setter
public class WallExplodeMsg extends Msg {
    UUID id;
    private int x;
    private int y;

    public WallExplodeMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public WallExplodeMsg() {

    }

    public WallExplodeMsg(Tank tank) {
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    @Override
    public void handle() {
        if (id.equals(GameModel.getInstance().getMyTank().getId())
                && !(GameModel.getInstance().getByUUId(this.id) instanceof Tank)) {
            return;
        }
        Tank tank = (Tank) GameModel.getInstance().getByUUId(id);
        if (tank != null) {
            tank.setMoving(false);
            tank.setX(x);
            tank.setY(y);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        byte[] bytes = null;
        try {
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);

            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            id = new UUID(dis.readLong(), dis.readLong());
            x = dis.readInt();
            y = dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.WallExplode;
    }
}
