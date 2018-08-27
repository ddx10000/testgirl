package com.example.demo.testAnnotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author DongDexuan
 * @date 2018/8/9 9:23
 * @desc
 */
public class TestMyRepeatableAnnotation {

    @MyRepeatableAnnotation("xyx")
    @MyRepeatableAnnotation("xyx")
    public void repeatable1(){}

    @Test
    public void test1() throws NoSuchMethodException {
        Method repeatable1 = this.getClass().getMethod("repeatable1", null);
        Annotation[] annotations = repeatable1.getAnnotations();
        System.out.println("repeatable1" + Arrays.asList(annotations));
    }
}
