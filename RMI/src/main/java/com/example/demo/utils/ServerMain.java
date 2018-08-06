package com.example.demo.utils;


import com.example.demo.compent.ServerSocketFactory;
import com.example.demo.server.HelloService;
import com.example.demo.server.impl.HelloServiceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

/**
 * 服务端客户端
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            HelloService helloService=new HelloServiceImpl();
            LocateRegistry.createRegistry(8888);
            //指定通讯端口
            RMISocketFactory.setSocketFactory(new ServerSocketFactory());
            Naming.bind("rmi://localhost:8888/helloService",helloService);
            System.out.println("BIND...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
