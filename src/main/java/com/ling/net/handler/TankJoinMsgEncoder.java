package com.ling.net.handler;

import com.ling.net.message.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 *
 * @author zhangling  2021/12/12 12:10
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf byteBuf) throws Exception {
        // byteBuf.writeBytes(msg.toBytes());
        // 分别写消息类型，消息长度，消息内容
        byteBuf.writeInt(msg.getMsgType().ordinal());
        byte[] bytes = msg.toBytes();
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

    }
}
