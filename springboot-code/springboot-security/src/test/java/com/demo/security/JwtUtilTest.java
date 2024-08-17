package com.demo.security;

import com.demo.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author wangxing
 * @date 2024-08-12 22:42
 */
@Slf4j
public class JwtUtilTest {

    @Test
    void testCreateToken() {
        String token = JwtUtil.createToken("wangxing");
        log.info("token: {}", token);


    }
}
