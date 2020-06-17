package com.cht.springboot.demo.Demo6JDBC;

import com.cht.springboot.demo.Demo6JDBC.bean.Message;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;



public class BeanTester {
    @Test
    public void test1(){
        Message message = new Message(5,"Mark");
        System.out.println(message.toString());
        message.setName("Ken");
        message.setId(6);
        System.out.println(message.toString());
    }
}
