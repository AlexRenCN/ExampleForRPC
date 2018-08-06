package com.example.demo.utils;


import com.example.demo.server.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 客户端代码
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            HelloService helloService=(HelloService) Naming.lookup("rmi://localhost:8888/helloService");
            helloService.sayHello("world");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
