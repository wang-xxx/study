package com.demo.ioc.test;

import com.demo.ioc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangxing
 * @date 2024-07-20 11:31
 */
@Slf4j
@SpringBootTest
public class TestAOP {

    @Autowired
    private TestService testService;

    @Test
    void test() {
        int add = testService.add(1, 2);
        log.info("测试完毕add = {}", add);
    }
}
