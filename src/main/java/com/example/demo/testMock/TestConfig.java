package com.example.demo.testMock;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author DongDexuan
 * @date 2018-6-5 11:29
 * @desc
 */
@Configuration
public class TestConfig {
    @Primary
    @Bean(autowire = Autowire.BY_TYPE)
    public MyEntity getMyEntity() {
        MyEntity myEntity = new MyEntity();
        myEntity.setName("haha");
        System.out.println("++++++++++++++++++++++++++++++");
        return myEntity;
    }
}
