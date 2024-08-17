package com.demo.netty.test;

import com.demo.netty.client.NettyClient;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NettyClientTest {

    @Autowired
    private NettyClient nettyClient;

    Channel connect() {
        Channel channel = null;
        try {
            channel = nettyClient.startClient();
        } catch (Exception e) {
            log.error("netty客户端启动失败", e);
        }
        return channel;
    }

    @Test
    void testStartClient() {
        connect();
    }

    @Test
    void testHello() {
        Channel channel = connect();
        channel.writeAndFlush("hello");
    }

}
