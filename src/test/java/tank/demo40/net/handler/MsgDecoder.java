package tank.demo40.net.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import tank.demo40.net.message.*;

import java.util.List;

/**
 * @author zhangling  2021/12/12 21:23
 */
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        // TCP 拆包，粘包问题  Tank 33 个字节
        if (byteBuf.readableBytes() < 8) {
            // 处理消息头 int  + 消息类型 int
            return;
        }
        // 标记读指针
        byteBuf.markReaderIndex();
        // 1. 读取消息类型 和 消息长度
        MsgType msgType = MsgType.values()[byteBuf.readInt()];
        int length = byteBuf.readInt();
        if (byteBuf.readableBytes() < length) {
            // 重置读指针到标记位置
            byteBuf.resetReaderIndex();
            return;
        }
        // 2. 读取消息内容到 bytes
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Msg msg = null;
        // 3. 根据消息类型解析消息
        switch (msgType) {
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                break;
            case TankStop:
                msg = new TankStopMsg();
                break;
            case BulletNew:
                msg = new BulletNewMsg();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        list.add(msg);

    }
}
