package com.example.demo.Service.impl;

import com.example.demo.Service.JacksonSerializableService;
import com.example.demo.utils.FDateJsonDeseralizer;
import com.example.demo.utils.FDateJsonSerializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Date;

public class JacksonSerializableServiceImpl implements JacksonSerializableService {

    private static final ObjectMapper mapper=new ObjectMapper();
    static {
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS,true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        SimpleModule module=new SimpleModule("DateTimeModule",Version.unknownVersion());
        module.addSerializer(Date.class,new FDateJsonSerializer());
        module.addDeserializer(Date.class,new FDateJsonDeseralizer());
        mapper.registerModule(module);
    }


    @Override
    public <T> byte[] serialize(T obj) {
        if(obj==null) return new byte[0];
        try {
            String json=mapper.writeValueAsString(obj);
            return json.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        String json=new String(data);
        try {
            return mapper.readValue(json,tClass);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
