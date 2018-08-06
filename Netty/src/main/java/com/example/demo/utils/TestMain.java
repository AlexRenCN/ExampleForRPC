package com.example.demo.utils;

import com.example.demo.service.NettyService;
import com.example.demo.service.impl.NettyServiceImpl;

public class TestMain {
    private static final String tag="@……%";

    public static void main(String[] args) {
        NettyService nettyService=new NettyServiceImpl();
        nettyService.startNettyFixed();
    }
}
