package com.example.demo.service.bean;

import com.example.demo.service.HelloService;
import com.example.demo.service.HelloServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/soap/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return new SpringBus();
    }

    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl();
    }

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint=new EndpointImpl(springBus(),helloService());
        endpoint.publish("/helloService");
        return endpoint;
    }
}
