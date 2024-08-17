package com.demo.netty.test;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventLoopTest {

    public static void main(String[] args) {
        //DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();//普通任务、定时任务
        NioEventLoopGroup group = new NioEventLoopGroup(2);//IO、普通任务、定时任务
        //核心数
        //获取下一个时间循环对象
        /*int processorNum = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < processorNum * 2 + 1; i++) {
            System.out.println(group.next());
        }*/
        //执行普通任务
        group.next().submit(() -> {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            log.info("hello");
        });
        //执行定时任务
        /*group.next().scheduleAtFixedRate(() -> {
            log.info("ok");
        }, 0, 1, TimeUnit.SECONDS);*/

        log.info("main");
    }

}
