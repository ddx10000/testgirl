package com.example.demo.testAnnotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author DongDexuan
 * @date 2018/8/9 9:19
 * @desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(MyRepeatableAnnotations.class)
public @interface MyRepeatableAnnotation {
    String value();
}
