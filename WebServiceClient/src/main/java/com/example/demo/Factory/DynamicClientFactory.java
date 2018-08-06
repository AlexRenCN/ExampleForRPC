package com.example.demo.Factory;

import com.sun.xml.internal.ws.handler.ClientLogicalHandlerTube;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 采用动态调用的方式
 */
public class DynamicClientFactory {

    public static void main(String[] args) {
        JaxWsDynamicClientFactory factory=JaxWsDynamicClientFactory.newInstance();
        Client client=factory.createClient("http://localhost:8081/soap/helloService?wsdl");
        //client.getOutInterceptors().add(new ClientLogicalHandlerTube("name","password"));
        Object[] objects=new Object[0];
        try {
            objects=client.invoke("sayHello","world");
            System.out.println(objects[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
