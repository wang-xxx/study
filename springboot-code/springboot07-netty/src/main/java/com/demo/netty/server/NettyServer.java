package com.demo.netty.server;

import com.demo.netty.config.NettyConfig;
import com.demo.netty.decode.CustomByteDecode;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class NettyServer {

    @Autowired
    private NettyConfig nettyConfig;

    @PostConstruct
    public void startServer() {
        log.info("准备启动Netty服务器");
        // 1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                // 2.BossEventLoopGroup，WorkerEventLoopGroup(selector, thread)
                .group(new NioEventLoopGroup())
                // 3.选择ServerSocketChannel服务NIO实现
                .channel(NioServerSocketChannel.class)
                // 4.子处理器，boss:处理连接，worker:负责处理读写。决定了worker能执行哪些操作(handler处理器)
                .childHandler(
                        // 5.channel 代表和客户端进行数据读写的通道，Initializer：初始化，负责添加哪些handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                                nioSocketChannel.pipeline()
                                        // .addLast(new StringDecoder())// 字符串解码器：将ByteBuf转成字符串
                                        // .addLast(new NettyServerHandler())// 自定义handler
                                        .addLast(new CustomByteDecode())
                                ;
                            }
                        }).bind(nettyConfig.getPort());// 绑定端口
        log.info("Netty服务器启动成功{}", nettyConfig.getPort());
    }
}
