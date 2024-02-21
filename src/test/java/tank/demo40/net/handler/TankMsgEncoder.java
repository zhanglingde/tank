package tank.demo40.net.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import tank.demo40.net.message.TankMsg;

/**
 * 将TankMsg转换成字节数组，在网络中传输中能用字节
 * 序列化也可以，但是序列化占用内存太大
 */
public class TankMsgEncoder extends MessageToByteEncoder<TankMsg> {


    @Override
    protected void encode(ChannelHandlerContext ctx, TankMsg msg, ByteBuf buf) throws Exception {
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);
    }
}