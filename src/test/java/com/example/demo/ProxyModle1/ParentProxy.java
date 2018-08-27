package com.example.demo.ProxyModle1;

/**
 * @author DongDexuan
 * @date 2018-7-2 13:50
 * @desc
 */
public class ParentProxy extends Parent {

    public ParentProxy() {
        System.out.println("Proxy no param constructor");
    }

    public ParentProxy(String s) {
        super(s);
        System.out.println("Proxy has param constructor:" + s);
    }

    @Override
    public String get() {
        System.out.println("this is Proxy get");
        return super.get();
    }
    @Override
    public void s(){
        System.out.println("this is Proxy s");
        super.s();
    }
}
