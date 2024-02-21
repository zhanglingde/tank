package tank.demo40.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import tank.demo40.net.handler.ClientHandler;
import tank.demo40.net.handler.MsgDecoder;
import tank.demo40.net.handler.MsgEncoder;
import tank.demo40.net.message.Msg;

/**
 * netty客户端连接远程server
 */
public class Client {

    public static final Client INSTANCE = new Client();

    private Channel channel = null;

    public void connect() {
        // 事件处理线程池，读或写都是从线程池中取线程进行操作事件
        EventLoopGroup worker = new NioEventLoopGroup(1);

        try {
            // 辅助启动类
            Bootstrap bootstrap = new Bootstrap();
            // 客户端连接服务器，netty一定是多线程的，group
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new MsgEncoder());
                    ch.pipeline().addLast(new MsgDecoder());
                    ch.pipeline().addLast(new ClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();


            /**
             * 异步关闭客户端
             *
             * 判断connect连接服务器是成功还是失败
             */
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                        worker.shutdownGracefully();
                    } else {
                        System.out.println("connected!");
                        // initialize the channel
                        channel = future.channel();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 客户端向服务器发送消息
     *
     * @param msg
     */
    public void send(Msg msg) {
        channel.writeAndFlush(msg);
    }

    /**
     * 发送特定字段表示退出与服务器的连接
     */
    public void closeConnect() {
        // this.send("_bye_");
    }


    public static void main(String[] args) throws Exception {
        // new Bootstrap()
        //         .group(new NioEventLoopGroup())
        //         .channel(NioSocketChannel.class)
        //         .handler(new ChannelInitializer<NioSocketChannel>() {
        //             @Override
        //             protected void initChannel(NioSocketChannel ch) throws Exception {
        //                 ch.pipeline().addLast(new StringEncoder());
        //             }
        //         })
        //         .connect(new InetSocketAddress("localhost", 8888))
        //         .sync()
        //         .channel()
        //         .writeAndFlush("hello,world");
        Client client = new Client();
        client.connect();
    }
}