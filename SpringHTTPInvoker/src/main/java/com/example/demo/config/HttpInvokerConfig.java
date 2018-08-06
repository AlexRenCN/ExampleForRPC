package com.example.demo.config;

import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class HttpInvokerConfig {

//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping(){
//        SimpleUrlHandlerMapping mapping=new SimpleUrlHandlerMapping();
//        Properties properties=new Properties();
//        properties.setProperty("/httpInvoker","httpInvokerServiceExporter");//第二个参数是springBean的名字
//        mapping.setMappings(properties);
//        return mapping;
//    }
//
    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl();
    }
    @Bean(name = "/helloService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(){
        HttpInvokerServiceExporter exporter=new HttpInvokerServiceExporter();
        exporter.setService(helloService());
        exporter.setServiceInterface(HelloService.class);
        return exporter;
    }
}
