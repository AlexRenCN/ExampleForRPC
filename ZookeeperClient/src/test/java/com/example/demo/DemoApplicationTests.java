package com.example.demo;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    ZkClient zkClient;

    @Test
    public void contextLoads() {
        String path = "/zk-data";
        //检查节点存在
        if(zkClient.exists(path)){
            //删除节点
            zkClient.delete(path);
        }
        if(zkClient.exists(path)){
            //递归删除节点
            zkClient.deleteRecursive(path);
        }
        //创建临时节点
        zkClient.createEphemeral("/test-data","临时节点");
        //创建临时有序节点
        zkClient.createEphemeralSequential("/test1-data","临时有序节点");
        //创建持久节点
        zkClient.createPersistent(path);
        //获得子节点
        List<String> childrens=zkClient.getChildren(path);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("Child change    "+s+"   list size   "+list.size());
            }
        });
        //写入数据
        zkClient.writeData(path,"test data");
        //读取数据
        System.out.println(zkClient.<String>readData(path,true));
        //zk监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("Data change!    "+s+"   data    "+o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("Data delete!    "+s);
            }
        });
        zkClient.writeData(path,"test data 2");
        zkClient.delete(path);
        //乐观锁操作
        zkClient.writeData("/test3-data",1,1);
        zkClient.writeData("/test3-date",2,2);


    }

}
