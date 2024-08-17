package com.demo.ffmpeg.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Semaphore;

/**
 * @author wangxing
 * @date 2024-08-08 12:26
 */
@Slf4j
@SpringBootTest
public class DownloadFactory {

    @Value("${max-video-download:10}")
    private int maxVideoDownload;

    private Semaphore downloadSemaphore = new Semaphore(maxVideoDownload);

    @Test
    void test() {
        log.info("maxVideoDownload:{}", maxVideoDownload);
        for (int i = 0; i < 35; i++) {
            DownloadThread thread = new DownloadThread(downloadSemaphore);
        }
    }
}

class DownloadThread extends Thread {

    private Semaphore downloadSemaphore;

    public DownloadThread(Semaphore downloadSemaphore) {
        this.downloadSemaphore = downloadSemaphore;
    }

    @Override
    public void run() {

    }
}
