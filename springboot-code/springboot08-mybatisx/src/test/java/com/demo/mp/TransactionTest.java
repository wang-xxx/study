package com.demo.mp;

import com.demo.mp.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private TestServiceImpl testServiceImpl;

    @Test
    void update() {
        testServiceImpl.testUpdate();
    }
}
