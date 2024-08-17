package com.demo.ffmpeg.test;

import com.demo.ffmpeg.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author wangxing
 * @date 2024-08-06 21:16
 */
@Slf4j
@SpringBootTest
public class VideoTest {

    @Autowired
    private VideoService videoService;

    @Test
    void testVideo() {
        String fileName = "D:\\test.mp4";
        String streamUrl = "rtmp://192.168.5.5:1935/live/test";

        File file = new File(fileName);
        assert file.exists() : "文件不存在";

        videoService.pushVideo2Srs(fileName, streamUrl);
    }

    @Test
    void testVideoConvert() throws Exception {
        videoService.videoConvert("D:\\test.mp4", "D:\\test_new.mp4", 5, 1000);
    }


    volatile boolean stopFlag = false;

    @Test
    void testCmd() throws Exception {
        // ffmpeg推流
        String cmd = "ffmpeg -i D://test.mp4  -vcodec libx264 -f flv rtmp://192.168.5.5:1935/live/test";
        Process process = Runtime.getRuntime().exec(cmd);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                log.info("准备结束推流...");
                stopFlag = true;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        while (true) {
            if (stopFlag) {
                if (process.isAlive()) {
                    process.destroy();
                }
                break;
            }
            log.info("继续推流...");
            Thread.sleep(100);
        }
        log.info("结束推流...");
    }

}
