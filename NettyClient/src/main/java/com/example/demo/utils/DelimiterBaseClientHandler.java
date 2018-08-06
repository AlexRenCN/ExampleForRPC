package com.example.demo.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;


public class DelimiterBaseClientHandler extends SimpleChannelInboundHandler {
    private static final AtomicInteger counter=new AtomicInteger(0);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String res=(String)o;
        System.out.println("response:   "+res+" counter "+counter.addAndGet(1));
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable t)throws Exception{
        channelHandlerContext.close();
    }
}
