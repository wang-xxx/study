package com.arthas.watch;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * watch命令监视：watch com.arthas.watch.WatchReturnObjTest test2 returnObj
 * @author wangxing
 * @date 2024-07-21 23:04
 */
@Slf4j
public class WatchReturnObjTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                test2(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private static List<Integer> test2(int num) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            list.add(random.nextInt(1000));
        }
        return list;
    }

}
