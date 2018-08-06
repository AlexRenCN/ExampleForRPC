package com.example.demo.utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class NettyDecoderHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //获取消息头锁标识的消息体字节数组长度
        if(byteBuf.readableBytes()<4){
            return;
        }
        byteBuf.markReaderIndex();
        int dataLenght=byteBuf.readInt();
        if(dataLenght<0){
            channelHandlerContext.close();
        }
        if(byteBuf.readableBytes()<dataLenght){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data=new byte[dataLenght];
        byteBuf.readBytes(data);
        ByteArrayInputStream inputStream=new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream=null;
        objectInputStream=new ObjectInputStream(inputStream);
        Object obj=(objectInputStream.readObject());
        list.add(obj);
    }
}
