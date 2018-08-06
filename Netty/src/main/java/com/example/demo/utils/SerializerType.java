package com.example.demo.utils;

import org.springframework.util.StringUtils;

public enum SerializerType {
    DefaultJavaSerializer("DefaultJavaSerializer"),
    HassianSerializer("HassianSerializer"),
    JSONSerializer("JSONSerializer"),
    ProtoStuffSerializer("ProtoStuffSerializer"),
    XmlSerializer("XmlSerializer"),
    MarshallingSerializer("MarshallingSerializer"),
    AvroSerializer("AvroSerializer"),
    ProtocolBufferSerializer("ProtocolBufferSerializer"),
    ThriftSerializer("ThriftSerializer");
    private String serializerType;
    private SerializerType(String SerializerType){
        this.serializerType=SerializerType;
    }

    public String getSerializerType() {
        return serializerType;
    }

    public static SerializerType queryByType(String serializerType){
        if(StringUtils.isEmpty(serializerType)){
            return null;
        }
        for (SerializerType type:SerializerType.values()){
            if(type.getSerializerType().equals(serializerType)){
                return type;
            }
        }
        return null;
    }
}