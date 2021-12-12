package com.ling.net.handler;

import com.ling.net.message.TankJoinMsg;
import com.ling.net.message.TankMsg;
import com.ling.tank.Dir;
import com.ling.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.UUID;

/**
 * 处理接收到的服务器消息
 *
 * @author zhangling  2021/12/12 11:50
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);

            System.out.println("客户端接收到服务器消息：" + new String(bytes));
            // 客户端接收到消息后，更新文本框
            String msgAccepted = new String(bytes);
            //ClientFrame.INSTANSE.updateText(msgAccepted);

        } finally {
            if (buf != null) {
                ReferenceCountUtil.release(buf);
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UUID id = UUID.randomUUID();
        ctx.writeAndFlush(new TankJoinMsg(5, 9, Dir.DOWN, true, Group.GOOD, id));
    }
}
