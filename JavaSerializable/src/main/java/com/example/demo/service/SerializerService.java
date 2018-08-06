package com.example.demo.service;

public interface SerializerService {
    <T>byte[] serialize(T obj);

    <T>T deserialize(byte[] data,Class<T> tClass);
}
