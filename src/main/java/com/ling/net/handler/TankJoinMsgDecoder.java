package com.ling.net.handler;

import com.ling.net.message.TankJoinMsg;
import com.ling.tank.Dir;
import com.ling.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @author zhangling  2021/12/12 12:12
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        // TCP 拆包，粘包问题  Tank 33 个字节
        if (byteBuf.readableBytes() < 33) {
            return;
        }
        TankJoinMsg msg = new TankJoinMsg();

        msg.setX(byteBuf.readInt());
        msg.setY(byteBuf.readInt());
        msg.setDir(Dir.values()[byteBuf.readInt()]);
        msg.setMoving(byteBuf.readBoolean());
        msg.setGroup(Group.values()[byteBuf.readInt()]);
        msg.setId(new UUID(byteBuf.readLong(), byteBuf.readLong()));

        list.add(msg);
    }
}
