package com.example.demo.testThis;

/**
 * @author DongDexuan
 * @date 2018-7-2 13:50
 * @desc
 */
public class Children extends Parent{

    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }


    public Children() {
        System.out.println("Children no param constructor");
    }

    public Children(String s) {
        super(s);
        this.s = s;
        System.out.println("Children has param constructor:" + s);
    }

    @Override
    public String get() {
        System.out.println("this is children get");
        return super.get();
    }
    @Override
    public void s(){
        super.s();
        System.out.println("this is children s");
    }
}
