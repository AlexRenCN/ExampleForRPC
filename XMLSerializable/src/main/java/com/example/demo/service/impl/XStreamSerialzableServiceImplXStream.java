package com.example.demo.service.impl;

import com.example.demo.service.XStreamSerializableService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamSerialzableServiceImplXStream implements XStreamSerializableService {
    private static final XStream xstream=new XStream(new DomDriver());

    @Override
    public <T> byte[] serialize(T obj) {
        return xstream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        String xml=new String(data);
        return (T)xstream.fromXML(xml);
    }
}
