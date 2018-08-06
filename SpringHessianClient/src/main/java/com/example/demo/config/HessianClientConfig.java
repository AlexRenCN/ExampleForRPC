package com.example.demo.config;

import com.caucho.hessian.client.HessianProxyFactory;
import com.example.demo.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.net.MalformedURLException;

@Configuration
public class HessianClientConfig {

    @Bean
    public HessianProxyFactory hessianProxyFactory(){
        HessianProxyFactory factory=new HessianProxyFactory();
        return factory;
    }
    @Bean
    public HelloService helloService(){
        try {
            return (HelloService)hessianProxyFactory().create(HelloService.class,"http://localhost:8080/helloService");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
