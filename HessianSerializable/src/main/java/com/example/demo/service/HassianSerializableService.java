package com.example.demo.service;

public interface HassianSerializableService {
    <T>byte[] serialize(T obj);

    <T>T deserialize(byte[] data,Class<T> tClass);

}
