//package com.example.demo.utils;
//
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.Semaphore;
//
//public class NettyServerInvokerHandler extends SimpleChannelInboundHandler {
//    //使用信号量限流
//    private static final Map<String,Semaphore> serviceKeySemaphoreMap=new ConcurrentHashMap<>();
//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AresRequest request) throws Exception {
////        if(channelHandlerContext.channel().isWritable()){
////            ProviderService metaDataModel=request.getProviderService();
////            final String methodName=request.getInvokedMethodName();
////            String serviceKey=metaDataModel.getServiceItf().getName();
////            int workerThread=metaDataModel.getWorkerThreads();
////            Semaphore semaphore=serviceKeySemaphoreMap.get(serviceKey);
////            if(semaphore==null){
////                synchronized (serviceKeySemaphoreMap){
////                    semaphore=serviceKeySemaphoreMap.get(serviceKey){
////                        if(semaphore==null){
////                            semaphore=new Semaphore(workerThread);
////                            serviceKeySemaphoreMap.put(serviceKey,semaphore);
////                        }
////                    }
////                }
////            }
////            IRegi
////        }
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception{
//        channelHandlerContext.flush();
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext channelHandlerContext,Throwable t)throws Exception{
//        t.printStackTrace();
//        channelHandlerContext.close();
//    }
//}
