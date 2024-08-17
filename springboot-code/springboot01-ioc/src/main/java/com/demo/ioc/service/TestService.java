package com.demo.ioc.service;

/**
 * @author wangxing
 * @date 2024-07-20 11:31
 */
public interface TestService {

    /**
     * 计算两数之和
     *
     * @param a
     * @param b
     * @return
     */
    int add(int a, int b);

    String testHotswap(Long id);
}
