package com.example.demo.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedLengthClientHandler extends SimpleChannelInboundHandler {
    public static final AtomicInteger counter=new AtomicInteger(0);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String req=(String)o;
        System.out.println("response    "+req+" count   "+counter.addAndGet(1));
    }
}
