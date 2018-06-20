package com.example.demo.me;

//@Component
public class Apple {
    private ThreadLocal<String> filed = new ThreadLocal<>();
    private static ThreadLocal<String> staticFiled = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "dong dexuan";
        }
    };

    public String getFiled() {
        return filed.get();
    }

    public void setFiled(String filed) {
        this.filed.set(filed);
    }

    public static String getStaticFiled() {
        return staticFiled.get();
    }

    public static void setStaticFiled(String staticFiled) {
        Apple.staticFiled.set(staticFiled);
    }
}
