package com.example.demo.service;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {

    @WebMethod
    String sayHello(@WebParam(name = "name")String name);
}
