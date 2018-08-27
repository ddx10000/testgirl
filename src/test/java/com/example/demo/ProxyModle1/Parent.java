package com.example.demo.ProxyModle1;

/**
 * @author DongDexuan
 * @date 2018-7-2 13:49
 * @desc
 */
public class Parent {

    public Parent() {
        System.out.println("parent no param constructor");
    }

    public Parent(String s) {
        System.out.println("parent has param constructor:" + s);
    }

    public String get() {
        System.out.println("this is parent get");
        s();
        return this.toString();
    }

    public void s() {
        System.out.println("this is parent s");
    }
}
