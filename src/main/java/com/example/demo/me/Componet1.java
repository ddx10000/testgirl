package com.example.demo.me;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Componet1 {
    public Componet1(){
        System.out.println("执行Componet1 Construct: PostConstruct");
    }
    @PostConstruct
    public void PostConstruct() {
        System.out.println("执行Componet1 PostConstructInit: PostConstruct");
    }

    @PreDestroy
    public void PreDestory(){
        System.out.println("执行Componet1 PostConstructInit: PreDestory");
    }

    public String process(){
        return "componet1";
    }
}
