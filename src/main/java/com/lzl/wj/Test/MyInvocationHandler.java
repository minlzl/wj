package com.lzl.wj.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MyInvocationHandler implements InvocationHandler {

    private Object object = null;
    public  Object bind(Object object) {
        this.object = object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "方法的参数是: " + Arrays.toString(args));
        Object result = method.invoke(this.object, args);
        System.out.println(method.getName() + "结果" + result);
        return null;
    }
}
