package com.example.demo.service.impl;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.example.demo.service.HassianSerializableService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HassianSerializableServiceImpl implements HassianSerializableService {
    @Override
    public <T> byte[] serialize(T obj) {
        if(null==obj){
            throw new NullPointerException();
        }
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        HessianOutput ho=new HessianOutput(out);
        try {
            ho.writeObject(obj);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        if(null==data){
            throw new NullPointerException();
        }
        ByteArrayInputStream in=new ByteArrayInputStream(data);
        HessianInput hi=new HessianInput(in);
        try {
            return (T)hi.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
