package com.example.demo.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author DongDexuan
 * @date 2018-6-20 11:33
 * @desc
 */
@RestController
@RequestMapping("test3")
public class Test3Controler {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test2")
    public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String forObject = restTemplate.getForObject("http://localhost:8080/test3/test3", String.class, new HashMap<>());
        System.out.println(forObject);
    }

    @GetMapping("/test3")
    public String test3(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "sucessed";
    }
}
