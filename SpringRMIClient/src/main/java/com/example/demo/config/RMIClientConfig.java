package com.example.demo.config;

import com.example.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RMIClientConfig {

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean(){
        RmiProxyFactoryBean factoryBean=new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://172.31.50.204:1099/helloService");
        factoryBean.setServiceInterface(HelloService.class);
        return factoryBean;
    }
}

