package com.demo.aop.before;

import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    public void test() {
        CalculatorImpl calculator = new CalculatorImpl();
        CalculatorImplProxy proxy = new CalculatorImplProxy(calculator);
        proxy.add(3, 5);
    }

    @Test
    public void TestDynamic() {
        // 创建目标对象
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        ProxyFactory<CalculatorImpl> proxyFactory = new ProxyFactory(calculatorImpl);
        Calculator proxy = proxyFactory.getProxy();
        proxy.add(1, 4);
        proxy.sub(3, 2);
        proxy.mul(2, 3);
        proxy.div(10, 2);
    }

}