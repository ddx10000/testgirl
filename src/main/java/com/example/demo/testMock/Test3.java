package com.example.demo.testMock;

import com.example.demo.me.Apple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test3 extends Apple{

@Autowired
private MyEntity myEntity;

    @GetMapping(value = "/getMyEntity")
    public String getFatherFiled(){
        return myEntity.getName();

    }

}
