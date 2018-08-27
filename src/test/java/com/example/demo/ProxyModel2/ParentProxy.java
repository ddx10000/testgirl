package com.example.demo.ProxyModel2;

/**
 * @author DongDexuan
 * @date 2018-7-2 13:50
 * @desc
 */
public class ParentProxy implements MyInterface {

    MyInterface myInterface = new Parent();

    public ParentProxy() {
        System.out.println("Proxy no param constructor");
    }

    @Override
    public String get() {
        System.out.println("this is Proxy get");
        return myInterface.get();
    }
    @Override
    public void s(){
        System.out.println("this is Proxy s");
        myInterface.s();
    }
}
