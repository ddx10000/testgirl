package com.example.demo.reflection;

import java.lang.reflect.Method;

public class MethodAccess {
    private Object obj;
    private Method method;

    public MethodAccess(Object obj, Method method) {
        super();
        this.obj = obj;
        this.method = method;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
