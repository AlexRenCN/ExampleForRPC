package com.example.demo.service;

public interface JavaSerializableService {
    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data,Class<T> tClass);

}
