package com.demo.ffmpeg.service;

/**
 * @author wangxing
 * @date 2024-08-06 21:08
 */
public interface VideoService {

    void pushVideo2Srs(String videoFile, String streamUrl);

    void videoConvert(String inputfile, String outputfile, Integer retryCount, Integer sleepTime) throws Exception;

}
