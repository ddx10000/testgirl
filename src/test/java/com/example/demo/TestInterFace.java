package com.example.demo;

@FunctionalInterface
public interface TestInterFace {
    boolean get();
    default void test(){
        System.out.println("haha");
    }
    static void testt(){
        System.out.println("zenm");
    }


    static void main(String[] args) {
        TestInterFace testInterFace = () -> false;
        testInterFace.test();
        testt();
    }
}
