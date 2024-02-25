package tank.demo40.net.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import tank.demo40.facade.GameModel;
import tank.demo40.net.message.Msg;
import tank.demo40.net.message.TankJoinMsg;

/**
 * 处理接收到的服务器消息
 *
 * @author zhangling  2021/12/12 11:50
 */
public class ClientHandler extends SimpleChannelInboundHandler<Msg> {

    /**
     * 接收服务器消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        // System.out.println("接收服务器消息。。。");
        // System.out.println("msg = " + msg);
        msg.handle();
    }

    /**
     * 客户端启动后触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(GameModel.getInstance().getMyTank()));
    }
}
// public class ClientHandler extends ChannelInboundHandlerAdapter {
//
//     @Override
//     public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//         ByteBuf buf = null;
//         try {
//             buf = (ByteBuf) msg;
//             byte[] bytes = new byte[buf.readableBytes()];
//             buf.getBytes(buf.readerIndex(), bytes);
//
//             System.out.println("客户端接收到服务器消息：" + new String(bytes));
//             // 客户端接收到消息后，更新文本框
//             String msgAccepted = new String(bytes);
//             //ClientFrame.INSTANSE.updateText(msgAccepted);
//
//         } finally {
//             if (buf != null) {
//                 ReferenceCountUtil.release(buf);
//             }
//         }
//     }
//
//     @Override
//     public void channelActive(ChannelHandlerContext ctx) throws Exception {
//         ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMyTank()));
//     }
// }
