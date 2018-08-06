package com.example.demo.bean;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private transient Integer age;
    private void writeObject(ObjectOutputStream out)throws IOException{
        out.defaultWriteObject();
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in)throws Exception{
        in.defaultReadObject();
        age=in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
