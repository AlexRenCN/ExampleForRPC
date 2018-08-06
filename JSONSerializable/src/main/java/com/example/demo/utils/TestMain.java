package com.example.demo.utils;

import com.example.demo.Service.FastjsonSerializableService;
import com.example.demo.Service.JacksonSerializableService;
import com.example.demo.Service.impl.FastjsonSerializableServiceImpl;
import com.example.demo.Service.impl.JacksonSerializableServiceImpl;
import com.example.demo.beam.User;

public class TestMain {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Alex");
        user.setAge(24);
//        JacksonSerializableService service = new JacksonSerializableServiceImpl();
//        byte[] bytes = service.serialize(user);
//        User user1 = service.deserialize(bytes, User.class);
//        System.out.println(user1);
        FastjsonSerializableService service=new FastjsonSerializableServiceImpl();
        byte[] bytes = service.serialize(user);
        User user1 = service.deserialize(bytes, User.class);
        System.out.println(user1);
    }
}
