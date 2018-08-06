package com.example.demo.service.impl;

import com.example.demo.service.JavaSerializableService;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class JavaSerializableServiceImpl implements JavaSerializableService {
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        XMLEncoder xmlEncoder=new XMLEncoder(out,"utf-8",true,0);
        xmlEncoder.writeObject(obj);
        xmlEncoder.close();
        return out.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        XMLDecoder xmlDecoder=new XMLDecoder(new ByteArrayInputStream(data));
        Object obj=xmlDecoder.readObject();
        xmlDecoder.close();
        return (T)obj;
    }
}
