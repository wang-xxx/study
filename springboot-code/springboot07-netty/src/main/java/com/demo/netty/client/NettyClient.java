package com.demo.netty.client;

import com.demo.netty.config.NettyConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyClient {

    @Autowired
    private NettyConfig nettyConfig;

    private Channel channel;

    public Channel startClient() throws InterruptedException {
        log.info("准备启动Netty客户端");
        channel = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", nettyConfig.getPort()))
                .sync()
                .channel();
        log.info("Netty客户端启动成功{}", nettyConfig.getPort());
        return channel;
    }

}
