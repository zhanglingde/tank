package tank.demo40.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import tank.demo40.net.handler.ServerChildHandler;

@Slf4j
public class Server {

    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public static void main(String[] args) throws Exception {

        // 1. 启动器，负责组装 netty 组件，启动服务器
        ChannelFuture channelFuture = new ServerBootstrap()
                // 2.NioEventLoopGroup 参数一：负责客户端连接的线程池；参数二：负责客户端中 IO 事件的线程池
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                // 3. 服务器 ServerSocketChannel 的实现方式（NIO，BIO）
                .channel(NioServerSocketChannel.class)
                // 4. boss 负责处理客户端连接，worker(child)负责客户端读写，childHandler决定了 worker（child）能执行哪些操作（handler）
                .childHandler(
                        // 5. channel 代表和客户端进行数据读写的通道 Initializer 初始化，负责添加别的 handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new StringDecoder());
                                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                        System.out.println("msg = " + msg);
                                    }
                                });
                            }
                        })
                .bind(8888);
        channelFuture.sync();
        // 异步关系
        channelFuture.channel().closeFuture().sync();

    }

    /**
     * 服务器启动
     * @throws InterruptedException
     */
    public void serverStart(){

        // 负责客户端连接线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 负责处理客户端中的IO事件线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                // 连接成功之后调用回调方法初始化
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 一有客户端连接 ，就往服务器责任链上加一个 ServerChildHandler
                    // 服务器接收消息，直接转发出去，不进行解析
                    // ch.pipeline().addLast(new TankJoinMsgEncoder());
                    // ch.pipeline().addLast(new TankJoinMsgDecoder());
                    ch.pipeline().addLast(new ServerChildHandler());
                }
            });
            // 所有方法是异步的，加sync()同步，只有bind()成功，才会往下执行
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            ServerFrame.INSTANCE.updateServerMsg("server started!");

            // 调用close()方法，返回ChannelFuture,否则一直阻塞在这里，直到调用了close()
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
