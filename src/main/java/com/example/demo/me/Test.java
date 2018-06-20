package com.example.demo.me;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test extends Apple{

//@Autowired
//private Apple apple;

    @GetMapping(value = "/getFatherFiled")
    public String getFatherFiled(){
        System.out.println(Thread.currentThread());
        return getFiled();

    }

    @GetMapping(value = "/settFatherFiled")
    public String settFatherFiled(){
        System.out.println(Thread.currentThread());
        setFiled("dong");
        return getFiled();

    }
    @GetMapping(value = "/settFatherFiled1")
    public String settFatherFiled1(){
        System.out.println(Thread.currentThread());
        setFiled("dong dexuan");
        return getFiled();

    }

    @GetMapping(value = "/getFatherStaticFiled")
    public String getFatherStaticFiled(){
        return getStaticFiled();

    }

    @GetMapping(value = "/settFatherStaticFiled")
    public String settFatherStaticFiled(){
        getStaticFiled();
        setStaticFiled("dexuan");
        return getStaticFiled();

    }

    @GetMapping(value = "/settFatherStaticFiled1")
    public String settFatherStaticFiled1(){
        setStaticFiled("dexuan.dong");
        return getStaticFiled();

    }
}
