package tank.demo40.net.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tank.demo40.net.Server;

/**
 * @author zhangling  2021/12/12 11:27
 */
public class ServerChildHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接触发该方法
     * 将客户端 Channel 就加入 clients,后续服务器遍历 clients 向所有客户端广播消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    /**
     * 客户端写事件时
     * 事件处理，有客户端写数据时，该方法会被自动调用（从workerGroup中取一个线程池调用）
     *
     * @param ctx
     * @param msg ByteBuf传递的数据内容
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 读取客户端写的数据，转发消息
        ByteBuf buf = null;
        // TankJoinMsg tm = (TankJoinMsg) msg;
        // System.out.println(tm);
        Server.clients.writeAndFlush(msg);
        // try {
        //     TankJoinMsg tm = (TankJoinMsg) msg;
        //     System.out.println(tm);
        //
        // } finally {
        //     if (msg != null) {
        //         ReferenceCountUtil.release(msg);
        //     }
        // }
    }

    /**
     * 发生异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 删除出现异常的客户端channel，并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
