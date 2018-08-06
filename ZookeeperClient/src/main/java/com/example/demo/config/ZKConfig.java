package com.example.demo.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZKConfig {
    @Bean
    public ZkClient zkClient(){
        String zkServers="127.0.0.1:2181";
        int connectionTimeOut=3000;
        ZkClient zkClient=new ZkClient(zkServers,connectionTimeOut);
        return zkClient;
    }
}
