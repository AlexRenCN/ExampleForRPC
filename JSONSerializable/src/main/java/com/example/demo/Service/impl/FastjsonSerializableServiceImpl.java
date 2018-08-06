package com.example.demo.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.Service.FastjsonSerializableService;

public class FastjsonSerializableServiceImpl implements FastjsonSerializableService {
    @Override
    public <T> byte[] serialize(T obj) {
        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
        return JSON.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return (T)JSON.parseObject(new String(data),tClass);
    }
}
