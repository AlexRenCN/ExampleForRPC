package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 直接链接
 */
public class EchoServerHandler extends SimpleChannelInboundHandler {
    //接收的处理
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //将客户端发送的数据转换成byte数组
        ByteBuf byteBuf=(ByteBuf)o;
        byte[] bytes=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        //展示客户端发送数据
        String request=new String(bytes,"UTF-8");
        System.out.println(request);

        ByteBuf response=Unpooled.copiedBuffer(request.getBytes());
        channelHandlerContext.write(response);
    }
    //异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable t) throws Exception{
        channelHandlerContext.close();
    }
    //缓冲区文本写入Channel
    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception{
        channelHandlerContext.flush();
    }
}
