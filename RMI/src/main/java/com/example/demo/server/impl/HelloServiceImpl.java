package com.example.demo.server.impl;


import com.example.demo.server.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服务端代码
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    private static final long serialVersionUID=1L;
    public HelloServiceImpl()throws RemoteException{
        super();
    }

    @Override
    public void sayHello(String name) throws RemoteException {
        System.out.println("hello "+name);
    }
}
