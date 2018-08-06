package com.example.demo.Service;

public interface JacksonSerializableService {
    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] data,Class<T> tClass);

}
