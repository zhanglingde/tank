package tank.msg;

import com.ling.net.handler.TankJoinMsgDecoder;
import com.ling.net.handler.TankJoinMsgEncoder;
import com.ling.net.message.TankJoinMsg;
import com.ling.tank.Dir;
import com.ling.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

/**
 * @author zhangling  2021/12/12 12:19
 */
public class TankJoinMsgCodecTest {

    @Test
    public void testEncoder() {
        // 一个虚拟的 Channel,测试用
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID uuid = UUID.randomUUID();

        // 将消息编码写出去
        TankJoinMsg msg = new TankJoinMsg(200, 300, Dir.UP, true, Group.BAD, uuid);
        ch.pipeline().addLast(new TankJoinMsgEncoder());
        ch.writeOutbound(msg);

        // 将消息读取出来
        ByteBuf byteBuf = ch.readOutbound();
        int x = byteBuf.readInt();
        int y = byteBuf.readInt();
        int dir = byteBuf.readInt();
        boolean moving = byteBuf.readBoolean();
        int group = byteBuf.readInt();
        long mostId = byteBuf.readLong();
        long leastId = byteBuf.readLong();

        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("Dir.values()[dir] = " + Dir.values()[dir]);
        System.out.println("moving = " + moving);
        System.out.println("Group.values()[group] = " + Group.values()[group]);
        System.out.println("new UUID(mostId,leastId).toString() = " + new UUID(mostId, leastId).toString());

        System.out.println("uuid = " + uuid);

    }

    @Test
    public void testDecoder() {
        // 一个虚拟的 Channel,测试用
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID uuid = UUID.randomUUID();

        // 将消息编码写出去
        TankJoinMsg msg = new TankJoinMsg(200, 300, Dir.UP, true, Group.BAD, uuid);
        ch.pipeline().addLast(new TankJoinMsgDecoder());
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBytes(msg.toBytes());
        // 将字节数组通过 byteBuf 写入 Channel
        ch.writeInbound(byteBuf.duplicate());

        // 通过 TankJoinDecoder 将字节数组解码成 TankJoinMsg 对象
        TankJoinMsg msgR = ch.readInbound();

        System.out.println("msgR.getX() = " + msgR.getX());
        System.out.println("msgR.getY() = " + msgR.getY());
        System.out.println("msgR.getDir() = " + msgR.getDir());
        System.out.println("msgR.isMoving() = " + msgR.isMoving());
        System.out.println("msgR.getGroup() = " + msgR.getGroup());
        System.out.println("msgR.getId() = " + msgR.getId());
    }
}
