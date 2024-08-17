package com.demo.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author wangxing
 * @date 2024-08-12 21:54
 */
public class JwtUtil {

    /**
     * 签名密钥
     */
    private static final String SIGN_KEY = "HfkjksFKLJISJFKLFKWJFQFIQWIOFJQOFFQGGSDGFFJIQOEUFIEJFIOQWEFHFQOK5FKOIQWUFFEFE423FIQEOFJHUEWHFKASKDLQWJIFSJDJKFHJIJWO";
    /**
     * 加密算法
     */
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS256;
    /**
     * token默认过期时间
     */
    private static final Long DEFAULT_EXPIRE_TIME = 1000 * 60 * 60 * 24L;

    /**
     * JWT header信息
     */
    private static Map<String, Object> getHeader() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("alg", ALGORITHM.getValue());
        map.put("typ", "JWT");
        return map;
    }

    /**
     * 生成token
     *
     * @param username 用户名
     * @return
     */
    public static String createToken(String username) {
        return createToken(username, DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 生成token
     *
     * @param username   用户名
     * @param expireTime 过期时间
     * @param timeUnit   时间单位
     * @return
     */
    public static String createToken(String username, Long expireTime, TimeUnit timeUnit) {
        // 载荷
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("param1", "1234567890");
        claims.put("param2", "abcdefg");
        // 过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, (int) timeUnit.toSeconds(expireTime));
        Date expireDate = calendar.getTime();
        // 生成token
        String token = Jwts.builder()
                .setHeader(getHeader())
                .setClaims(claims)
                .expiration(expireDate)
                .subject(username)
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                .compact();
        return token;
    }

    public void parseToken(String token) {

    }

}
