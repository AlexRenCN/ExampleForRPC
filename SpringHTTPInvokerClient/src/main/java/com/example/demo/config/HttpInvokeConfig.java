package com.example.demo.config;


import com.example.demo.service.HelloService;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpComponentsHttpInvokerRequestExecutor;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class HttpInvokeConfig {

    @Bean
    public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean(){
        HttpInvokerProxyFactoryBean factoryBean=new HttpInvokerProxyFactoryBean();
        factoryBean.setServiceUrl("http://localhost:8080/helloService");
        factoryBean.setServiceInterface(HelloService.class);
        return factoryBean;
    }
   @Bean
   public HttpClient httpClient(){
       HttpClient httpClient=new DefaultHttpClient();
       return httpClient;
   }

//    @Bean
//    public HttpComponentsHttpInvokerRequestExecutor httpComponentsHttpInvokerRequestExecutor() {
//        HttpComponentsHttpInvokerRequestExecutor executor = new HttpComponentsHttpInvokerRequestExecutor();
//        executor.setHttpClient(httpClient());
//        return executor;
//    }

}
