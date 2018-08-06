package com.example.demo.utils;

import com.example.demo.bean.User;
import com.example.demo.service.JavaSerializableService;
import com.example.demo.service.XStreamSerializableService;
import com.example.demo.service.impl.JavaSerializableServiceImpl;
import com.example.demo.service.impl.XStreamSerialzableServiceImplXStream;

public class TestMain {
    public static void main(String[] args) {
        User user=new User();
        user.setName("Alex");
        user.setAge(24);
//        XStreamSerializableService service=new XStreamSerialzableServiceImplXStream();
//        byte[] bytes=service.serialize(user);
//        User user1=service.deserialize(bytes,User.class);
//        System.out.println(user1.toString());
        JavaSerializableService service=new JavaSerializableServiceImpl();
        byte[] bytes=service.serialize(user);
        User user1=service.deserialize(bytes,User.class);
        System.out.println(user1.toString());
    }
}
