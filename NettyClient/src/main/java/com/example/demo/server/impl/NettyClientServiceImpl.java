package com.example.demo.server.impl;

import com.example.demo.server.NettyClientService;
import com.example.demo.utils.DelimiterBaseClientHandler;
import com.example.demo.utils.EchoClientHandler;
import com.example.demo.utils.LineBasedClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClientServiceImpl implements NettyClientService {
    @Override
    public void nettyClient() {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });
        try {
            //创建异步链接
            ChannelFuture f=b.connect("127.0.0.1",8080).sync();
            byte[] request="hello world".getBytes();
            ByteBuf byteBuf=Unpooled.buffer(request.length);
            byteBuf.writeBytes(request);
            //发送数据
            ChannelFuture channelFuture=f.channel().writeAndFlush(byteBuf);
            channelFuture.syncUninterruptibly();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void nettyClient(String tag) {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        ByteBuf byteBuf=Unpooled.copiedBuffer(tag.getBytes());
                        nioSocketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,byteBuf));
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new DelimiterBaseClientHandler());
                    }
                });
        try {
            //创建异步链接
            ChannelFuture f=b.connect("127.0.0.1",8080).sync();
            for (int i=0;i<1000;i++) {
                byte[] request = ("hello world" + tag).getBytes();
                ByteBuf byteBuf = Unpooled.buffer(request.length);
                byteBuf.writeBytes(request);
                //发送数据
                ChannelFuture channelFuture = f.channel().writeAndFlush(byteBuf);
                channelFuture.syncUninterruptibly();
            }
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void nettyClientBlank() {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        nioSocketChannel.pipeline().addLast(new EchoClientHandler());
                        nioSocketChannel.pipeline().addLast(new LineBasedClientHandler());
                    }
                });
        try {
            //创建异步链接
            ChannelFuture f=b.connect("127.0.0.1",8080).sync();
            for (int i=0;i<1000;i++) {
                byte[] request = "hello world\n".getBytes();
                ByteBuf byteBuf = Unpooled.buffer(request.length);
                byteBuf.writeBytes(request);
                //发送数据
                ChannelFuture channelFuture = f.channel().writeAndFlush(byteBuf);
                channelFuture.syncUninterruptibly();
            }
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void nettyClientFixed() {
        EventLoopGroup group=new NioEventLoopGroup();
        Bootstrap b=new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });
        try {
            //创建异步链接
            ChannelFuture f=b.connect("127.0.0.1",8080).sync();
            byte[] request="hello world".getBytes();
            for(int i=0;i<1000;i++) {
                ByteBuf byteBuf = Unpooled.buffer(request.length);
                byteBuf.writeBytes(request);
                //发送数据
                ChannelFuture channelFuture = f.channel().writeAndFlush(byteBuf);
                channelFuture.syncUninterruptibly();
            }
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
