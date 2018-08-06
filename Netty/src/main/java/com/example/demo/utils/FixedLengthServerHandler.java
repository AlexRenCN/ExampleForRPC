package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 固定长度的包
 */
public class FixedLengthServerHandler extends SimpleChannelInboundHandler {
    public static final AtomicInteger counter=new AtomicInteger(0);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String res=(String)o;
        System.out.println("request "+res+" count   "+counter.addAndGet(1));
        ByteBuf byteBuf=Unpooled.copiedBuffer(res.getBytes());
        channelHandlerContext.writeAndFlush(byteBuf);
    }
    //异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable t) throws Exception {
        channelHandlerContext.close();
    }

    //缓冲区文本写入Channel
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception{
        channelHandlerContext.flush();

    }

    }
