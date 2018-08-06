package com.example.demo.service.impl;

import com.example.demo.service.NettyService;
import com.example.demo.utils.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.Serializable;

public class NettyServiceImpl implements NettyService {
    @Override
    public void startNetty() {
        //用于处理网络事件的线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();//对外提供服务的线程组
        EventLoopGroup workerGroup=new NioEventLoopGroup();//对内处理的线程组
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup)//父线程组-子线程组
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)//TCP参数，连接请求的最大队列数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new EchoServerHandler());
                    }
                });

        try {
            ChannelFuture f=b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    /**
     * 使用特殊结尾字符，避免半包、粘包发生
     * @param tag
     */
    @Override
    public void startNetty(String tag) {
        //用于处理网络事件的线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup)//父线程组-子线程组
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)//TCP参数，连接请求的最大队列数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ByteBuf byteBuf=Unpooled.copiedBuffer(tag.getBytes());
                        nioSocketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new DelimiterBaseServerHandler());
                    }
                });

        try {
            ChannelFuture f=b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 使用换行符避免半包粘包
     */
    @Override
    public void startNettyBlank() {
        //用于处理网络事件的线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup)//父线程组-子线程组
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)//TCP参数，连接请求的最大队列数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline()
                                .addLast(new LineBasedFrameDecoder(1024));
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new LineBasedServerHandler());
                    }
                });

        try {
            ChannelFuture f=b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     *
     */
    @Override
    public void startNettyFixed() {
        //用于处理网络事件的线程组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        ServerBootstrap b=new ServerBootstrap();
        b.group(bossGroup,workerGroup)//父线程组-子线程组
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)//TCP参数，连接请求的最大队列数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new FixedLengthFrameDecoder(11));
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new FixedLengthServerHandler());
                    }
                });

        try {
            ChannelFuture f=b.bind(8080).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public void startNettyProtobuf() {

    }
    private EventLoopGroup nettyBossGroup;

    private EventLoopGroup nettyWorkerGroup;


    @Override
    public void netty(final int port) {
//        synchronized (NettyServiceImpl.class){
//            if(nettyBossGroup!=null || nettyWorkerGroup!=null);
//            {
//                return;
//            }
//            //用于处理网络事件的线程组
//            nettyBossGroup=new NioEventLoopGroup();//对外提供服务的线程组
//            nettyWorkerGroup=new NioEventLoopGroup();//对内处理的线程组
//            ServerBootstrap b=new ServerBootstrap();
//            b.group(nettyBossGroup,nettyWorkerGroup)//父线程组-子线程组
//                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG,1024)//TCP参数，连接请求的最大队列数
//                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                        @Override
//                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//                            nioSocketChannel.pipeline().addLast(new NettyDecoderHandler());
//                            nioSocketChannel.pipeline().addLast(new NettyEncoderHandler());
//                            nioSocketChannel.pipeline().addLast(new NettyServerInvokerHandler());
//                        }
//                    });
//            try {
//                ChannelFuture f=b.bind(8080).sync();
//                f.channel().closeFuture().sync();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                //优雅退出
//                nettyBossGroup.shutdownGracefully();
//                nettyWorkerGroup.shutdownGracefully();
//            }
//        }
    }
}
