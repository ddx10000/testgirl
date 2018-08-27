package com.example.demo.testThis;

/**
 * @author DongDexuan
 * @date 2018/7/23 13:23
 * @desc
 */
public class MySingleton {
    public MySingleton() {
    }
    private static volatile MySingleton mySingleton;
    public static MySingleton getMySingleton(){
        if (mySingleton == null){
            synchronized (MySingleton.class){
                if (mySingleton == null){
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;
    }
}
