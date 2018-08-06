package com.example.demo.Factory;

import com.example.demo.service.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * 采用动态代理的方式调用
 */
public class ProxyFactory {
    public static void main(String[] args) {
        String address="http://localhost:8081/soap/helloService?wsdl";
        JaxWsProxyFactoryBean factoryBean=new JaxWsProxyFactoryBean();
        factoryBean.setAddress(address);
        factoryBean.setServiceClass(HelloService.class);
        HelloService helloService=(HelloService)factoryBean.create();
        String name="world";
        String result=helloService.sayHello(name);
        System.out.println(result);

    }
}
