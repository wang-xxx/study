package com.demo.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangxing
 * @date 2024-07-16 08:28
 */
@Slf4j
public class CustomServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到客户端消息:{}", msg);
        super.channelRead(ctx, msg);
    }
}
