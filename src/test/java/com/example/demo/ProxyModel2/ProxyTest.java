package com.example.demo.ProxyModel2;

import org.junit.Test;

/**
 * @author DongDexuan
 * @date 2018/7/25 17:20
 * @desc
 */
public class ProxyTest {

    @Test
    public void test(){
        ParentProxy parentProxy = new ParentProxy();
        parentProxy.get();
        parentProxy.s();
    }
}
