package com.demo.ioc.service.impl;

import com.demo.ioc.annotation.TestLog;
import com.demo.ioc.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author wangxing
 * @date 2024-07-20 11:32
 */
@Service
public class TestServiceImpl implements TestService {

    @TestLog
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public String testHotswap(Long id) {
        id = Long.valueOf(new Random().nextInt(30));
        id = 40L;
        String value = String.valueOf(id);
        return "hello world！" + value;
    }
}
