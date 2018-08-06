package com.example.demo.service;

public interface NettyService {

    void startNetty();

    void startNetty(String tag);

    void startNettyBlank();

    void startNettyFixed();

    void startNettyProtobuf();

    void netty(final int port);
}
