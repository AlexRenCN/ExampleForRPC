package com.example.demo.invoke;

import com.example.demo.framework.ConsumerProxy;
import com.example.demo.service.HelloService;

public class RpcConsumerMain {
    public static void main(String[] args) {
        try {
            HelloService helloService=ConsumerProxy.consume(HelloService.class,"127.0.0.1",8888);
            System.out.println(helloService.sayHello("world"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
