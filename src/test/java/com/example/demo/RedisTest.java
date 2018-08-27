package com.example.demo;

import com.example.demo.service.RedisService;
import com.example.demo.service.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisService redisService;

    @Before
    public void setUp() {

    }

    @Test
    public void get() {
        User user = new User();
        user.setUserName("wangjianfeng2");
        user.setPassWord("wangjianfeng2");
        redisService.add("userByName:" + user.getUserName(), user);
        List<User> list = new ArrayList<>();
        list.add(user);
        redisService.add("list", list, 10L);
        User user1 = redisService.get("userByName:wangjianfeng2",User.class);
        Assert.notNull(user1, "user is null");
    }
}