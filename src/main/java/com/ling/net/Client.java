package com.ling.net;

import com.ling.net.handler.ClientHandler;
import com.ling.net.handler.TankJoinMsgDecoder;
import com.ling.net.handler.TankJoinMsgEncoder;
import com.ling.net.handler.TankMsgEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;

/**
 * netty客户端连接远程server
 */
public class Client {

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
                    ch.pipeline().addLast(new TankJoinMsgEncoder());
                    ch.pipeline().addLast(new TankJoinMsgDecoder());
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
    public void send(String msg) {
        ByteBuf buf = Unpooled.copiedBuffer(msg.getBytes());
        channel.writeAndFlush(buf);
    }

    /**
     * 发送特定字段表示退出与服务器的连接
     */
    public void closeConnect() {
        this.send("_bye_");
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
