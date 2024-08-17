package com.demo.aop.before;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory<T> {

    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }

    public T getProxy() {
        Class<?> targetClass = target.getClass();
        //1.获取目标对象的类加载器
        ClassLoader classLoader = targetClass.getClassLoader();
        //2.获取目标对象实现的所有接口
        Class<?>[] interfaces = targetClass.getInterfaces();
        //jdk动态代理
        T proxyObject = (T) Proxy.newProxyInstance(
                classLoader,
                interfaces,
                (proxy, method, args) -> {//代理对象要执行的代码过程，method：目标方法，args：目标方法的参数
                    System.out.println(method.getName() + "方法被执行了，入参为：" + Arrays.toString(args));
                    //执行目标方法，并获取返回值
                    Object result = method.invoke(target, args);
                    System.out.println(method.getName() + "方法被执行了，返回值为：" + result);
                    return result;
                });
        return proxyObject;
    }

}
