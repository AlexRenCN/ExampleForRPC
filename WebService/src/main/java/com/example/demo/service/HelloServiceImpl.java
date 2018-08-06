package com.example.demo.service;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }
    //FIXME 如果没有手动指定命名空间，实现类和接口最好放在一个目录下
}
