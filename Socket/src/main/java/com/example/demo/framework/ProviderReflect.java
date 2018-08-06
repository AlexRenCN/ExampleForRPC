package com.example.demo.framework;

import org.apache.commons.beanutils.MethodUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProviderReflect {
    private static final ExecutorService executorService=Executors.newCachedThreadPool();

    public static void provider(final Object service,int port) throws Exception{
        ServerSocket serverSocket=new ServerSocket(port);
        while (true){
            final Socket socket=serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream inputStream=new ObjectInputStream(socket.getInputStream());
                        try {
                            try {
                                String methodName=inputStream.readUTF();
                                Object[] args=(Object[])inputStream.readObject();
                                ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    Object result=MethodUtils.invokeMethod(service,methodName,args);
                                    outputStream.writeObject(result);
                                }catch (Throwable t){
                                    outputStream.writeObject(t);
                                }finally {
                                    outputStream.close();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                inputStream.close();
                            }
                        }finally {
                            socket.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
