package com.ling.net.message;

import com.ling.net.Client;
import com.ling.tank.Dir;
import com.ling.tank.Group;
import com.ling.tank.Tank;
import com.ling.tank.TankFrame;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;
import java.util.UUID;

/**
 * @author zhangling  2021/12/12 11:59
 */
@Getter
@Setter
@ToString
public class TankJoinMsg extends Msg{
    private int x;
    private int y;
    private Dir dir;
    private boolean moving;
    private Group group;
    private UUID id;

    public TankJoinMsg(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.moving = tank.isMoving();
        this.group = tank.getGroup();
        this.id = tank.getId();
    }

    public TankJoinMsg() {
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    /**
     * 处理服务器发送的消息，在界面上新绘制一个 Tank
     */
    @Override
    public void handle() {

        // 接收到的消息是自己发出去的，或是已经处理过的消息，就不进行处理
        if (id.equals(TankFrame.INSTANCE.getMyTank().getId())
                || TankFrame.INSTANCE.findByUUId(id) != null) {
            return;
        }
        System.out.println(this);
        Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);

        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMyTank()));
    }

    /**
     * 将对象转换成字节数组，通过网络传输
     *
     * @return
     */
    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            // uuid 为 128 位，分成高 64 位，低 64 位；使用 Long 类型写出去（一个 Long 8 个字节 64 位）
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
                    dos.close();
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
            // 先读取消息类型，根据 Type 信息处理不同的消息
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(), dis.readLong());
            // this.name = dis.readUTF(); 读取字符串
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoin;
    }
}
