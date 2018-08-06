package com.example.demo.utils;

import com.example.demo.bean.User;
import com.example.demo.service.SerializerService;
import com.example.demo.service.impl.SerilalizerServiceImpl;

public class TestMain {
    public static void main(String[] args) {
        SerializerService service=new SerilalizerServiceImpl();
        User user=new User();
        user.setName("Alex");
        user.setAge(24);//在使用非序列化的属性时使用绕过机制
        byte[] bytes=service.serialize(user);
        User user1=service.deserialize(bytes,User.class);
        System.out.println(user1.toString());
    }
}
