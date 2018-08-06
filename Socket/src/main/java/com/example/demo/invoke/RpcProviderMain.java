package com.example.demo.invoke;

import com.example.demo.framework.ProviderReflect;
import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;

public class RpcProviderMain {
    public static void main(String[] args) {
        try {
            HelloService helloService=new HelloServiceImpl();
            ProviderReflect.provider(helloService,8888);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
