package com.example.demo.utils;

import com.example.demo.server.NettyClientService;
import com.example.demo.server.impl.NettyClientServiceImpl;

public class TestMain {
    private static final String tag="@……%";

    public static void main(String[] args) {
        NettyClientService service=new NettyClientServiceImpl();
        service.nettyClientFixed();
    }
}
