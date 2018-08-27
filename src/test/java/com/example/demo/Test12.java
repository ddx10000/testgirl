package com.example.demo;

/**
 * @author DongDexuan
 * @date 2018/8/22 18:29
 * @desc
 */
public class Test12 {

    private Test12(){}
    private static volatile Test12 test12;

    public static Test12 getInstance(){
        if (test12 == null){
            synchronized (Test12.class){
                if (test12 == null){
                    test12 = new Test12();
                }
            }
        }
        return test12;
    }

    public static void main(String[] args) {
        long lo = Integer.MAX_VALUE;
        int i = Integer.MAX_VALUE;
        System.out.println(i);
        System.out.println(lo > i);
        System.out.println(lo == i);
    }
}
