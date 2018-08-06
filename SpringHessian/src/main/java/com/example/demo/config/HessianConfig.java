package com.example.demo.config;

import com.example.demo.service.HelloService;
import com.example.demo.service.impl.HelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class HessianConfig {

    @Bean
    public HelloService helloService() {
        return new HelloServiceImpl();
    }

    @Bean(name = "/helloService")
    public HessianServiceExporter hessianServiceExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(helloService());
        exporter.setServiceInterface(HelloService.class);

        return exporter;
    }

//    @Bean(name = "/")
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        Properties properties = new Properties();
//        properties.setProperty("/helloService", "helloService");
//        mapping.setMappings(properties);
//        return mapping;
//    }
}
