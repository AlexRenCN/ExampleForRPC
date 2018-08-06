package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 */
public class EchoClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //读取相应数据
        ByteBuf byteBuf=(ByteBuf)o;
        byte[] res=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(res);
        System.out.println("===============================");
        System.out.println("get response:"+new String(res,"UTF-8"));
        System.out.println("===============================");
    }

}
