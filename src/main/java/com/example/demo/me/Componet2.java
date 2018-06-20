package com.example.demo.me;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Componet2 {

    public Componet2(){
        System.out.println("执行Componet2 Construct: PostConstruct");
    }

    @PostConstruct
    public void PostConstruct() {
        System.out.println("执行Componet2 PostConstructInit: PostConstruct");
    }

    @PreDestroy
    public void PreDestory(){
        System.out.println("执行Componet2 PostConstructInit: PreDestory");
    }

    public String process(){
        return "componet2";
    }
}
