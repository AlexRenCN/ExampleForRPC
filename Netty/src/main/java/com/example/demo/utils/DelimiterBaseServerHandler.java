package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用特殊标记符号标记包结束
 */
public class DelimiterBaseServerHandler extends SimpleChannelInboundHandler {
    private static final String tag="@……%";
    private static AtomicInteger counter=new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String req=(String)o;
        System.out.println("request: "+req);
        req+=tag;
        ByteBuf byteBuf=Unpooled.copiedBuffer(req.getBytes());
        channelHandlerContext.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable t)throws Exception{
        channelHandlerContext.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext)throws Exception{
        channelHandlerContext.flush();
    }
}
