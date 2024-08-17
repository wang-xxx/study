package com.demo.netty.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wangxing
 * @date 2024-08-07 16:42
 */
@Slf4j
public class ByteBufferTest {

    public static void main(String[] args) {
        // 准备输入流：1.FileChannel   2.RandomAccessFile
        try (FileChannel channel = new FileInputStream(ResourceUtils.getFile("classpath:test.txt")).getChannel();) {
            // 准备缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                // channel读取数据到缓冲区
                int len = channel.read(byteBuffer);
                log.info("读取到的字节数：{}", len);
                if (len < 0) {
                    break;
                }
                // 读取缓冲区数据
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    log.info("读取数据：{}", (char) b);
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            log.info("文件发生异常", e);
        }
    }

}