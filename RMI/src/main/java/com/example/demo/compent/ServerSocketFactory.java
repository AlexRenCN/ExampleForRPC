package com.example.demo.compent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * 为了解决RMI的随机通讯端口可能被防火墙拦截，需要强制指定通讯端口
 */
public class ServerSocketFactory extends RMISocketFactory {

    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host,port);
    }

    @Override
    public ServerSocket createServerSocket(int port) throws IOException {
        if(port==0){
            port=8866;
        }
        System.out.println("build serverSocket on port:"+port);
        return new ServerSocket(port);
    }
}
