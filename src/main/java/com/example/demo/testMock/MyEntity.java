package com.example.demo.testMock;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author DongDexuan
 * @date 2018-6-5 12:08
 * @desc
 */
@Component
@Data
public class MyEntity {
    private String name = "dong";

    public MyEntity() {
        System.out.println("--------------------");
    }
}
