package com.demo.aop.before;

public class CalculatorImplProxy implements Calculator {

    private Calculator calculator;

    //强制要求：创建代理对象，必须传入目标对象，才能使用代理
    public CalculatorImplProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int i, int j) {
        //非核心代码，eg：记录方法日志
        System.out.println("add方法执行了，入参是" + i + "," + j);

        //核心代码
        int result = calculator.add(i, j);

        System.out.println("add方法执行了，出参是" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result = i - j;
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;
        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = i / j;
        return result;
    }

}
