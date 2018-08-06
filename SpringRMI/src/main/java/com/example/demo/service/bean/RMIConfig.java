package com.example.demo.service.bean;

import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RMIConfig {



    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl();
    }


    @Bean
    public RmiServiceExporter rmiServiceExporter(){
        RmiServiceExporter rmiServiceExporter=new RmiServiceExporter();
        rmiServiceExporter.setService(helloService());
        rmiServiceExporter.setServiceName("helloService");
        rmiServiceExporter.setServiceInterface(HelloService.class);
        rmiServiceExporter.setServicePort(8666);
        return rmiServiceExporter;
    }
}
