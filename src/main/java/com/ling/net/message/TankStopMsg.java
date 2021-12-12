package com.ling.net.message;

import com.ling.tank.Tank;
import com.ling.tank.TankFrame;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.UUID;

/**
 * @author zhangling  2021/12/12 21:38
 */
@Getter
@Setter
public class TankStopMsg extends Msg {
    UUID id;
    private int x;
    private int y;

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg() {

    }

    public TankStopMsg(Tank tank) {
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    @Override
    public void handle() {
        if (id.equals(TankFrame.INSTANCE.getMyTank().getId())) {
            return;
        }
        Tank tank = TankFrame.INSTANCE.findByUUId(id);
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
        return MsgType.TankStop;
    }
}
