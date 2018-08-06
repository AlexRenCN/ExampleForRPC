package com.example.demo;

import com.example.demo.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private RmiProxyFactoryBean factoryBean;

    @Test
    public void contextLoads() {
        HelloService service= (HelloService) factoryBean.getObject();
        System.out.println(service.sayHello("world"));
    }

}
