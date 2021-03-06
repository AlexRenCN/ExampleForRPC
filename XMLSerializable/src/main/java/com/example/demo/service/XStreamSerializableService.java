package com.example.demo.service;

public interface XStreamSerializableService {

    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data,Class<T> tClass);
}
