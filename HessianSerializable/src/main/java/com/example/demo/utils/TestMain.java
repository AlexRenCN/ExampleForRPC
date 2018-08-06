package com.example.demo.utils;

import com.example.demo.bean.User;
import com.example.demo.service.HassianSerializableService;
import com.example.demo.service.impl.HassianSerializableServiceImpl;

public class TestMain {
    public static void main(String[] args) {
        User user=new User();
        user.setName("Alex");
        user.setAge(24);
        HassianSerializableService service=new HassianSerializableServiceImpl();
        byte[] bytes=service.serialize(user);
        User user1=service.deserialize(bytes,User.class);
        System.out.println(user1.toString());
    }
}
