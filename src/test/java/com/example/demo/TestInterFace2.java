package com.example.demo;

public abstract class TestInterFace2 {
    String a = "";
    Integer ab;
    abstract boolean get();

    void test() {

    }


    static void testt(){
        System.out.println("zenm");
    }

    public static void main(String[] args) {
        new TestInterFace2() {
            @Override
            boolean get() {
                return false;
            }
        };
    }
}