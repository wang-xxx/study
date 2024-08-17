package com.demo.ffmpeg.service.impl;

import com.demo.ffmpeg.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avformat;
import org.bytedeco.javacv.*;
import org.springframework.stereotype.Service;


/**
 * @author wangxing
 * @date 2024-08-06 21:09
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public void pushVideo2Srs(String videoFile, String streamUrl) {
        // 步骤一：准备FFmpeg库	    下载并配置FFmpeg库
        // 步骤二：准备音视频文件	    准备要推流的音视频文件
        // 步骤三：初始化FFmpeg	    初始化FFmpeg环境
        // 步骤四：打开输入文件	        打开要推流的音视频文件
        // 步骤五：打开输出URL	        打开要推流到的服务器URL
        // 步骤六：循环读取音视频数据	循环读取音视频数据并推流
        // 步骤七：释放资源	        释放FFmpeg相关资源
        AVFormatContext inputFormatContext = null;
        AVFormatContext outputFormatContext = null;
        try {
            inputFormatContext = avformat.avformat_alloc_context();
            outputFormatContext = avformat.avformat_alloc_context();
            // 使用AVFormatContext来打开要推流的音视频文件
            if (avformat.avformat_open_input(inputFormatContext, videoFile, null, null) != 0) {
                // 打开输入文件失败
                return;
            }
            // 你需要使用AVFormatContext来打开要推流到的服务器URL
            if (avformat.avformat_alloc_output_context2(outputFormatContext, null, "mpeg", streamUrl) < 0) {
                // 打开输出URL失败
                return;
            }
            // 循环读取音视频数据并推流到服务器
            AVPacket packet = avcodec.av_packet_alloc();
            while (avformat.av_read_frame(inputFormatContext, packet) >= 0) {
                // 将音视频数据写入输出URL
                avformat.av_write_frame(outputFormatContext, packet);
                avcodec.av_packet_unref(packet);
            }
        } catch (Throwable e) {
            log.error("视频推流错误", e);
        } finally {
            avformat.avformat_close_input(inputFormatContext);
            avformat.avformat_free_context(inputFormatContext);
            avformat.avformat_free_context(outputFormatContext);
        }
    }

    @Override
    public void videoConvert(String inputFile, String outputFile, Integer retryCount, Integer sleepTime) throws Exception {
        if (outputFile.lastIndexOf('.') < 0) {
            throw new Exception("Error! Output file format undetected!");
        }
        String format = outputFile.substring(outputFile.lastIndexOf('.'));

        FFmpegLogCallback.set();
        Frame frame;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        FFmpegFrameRecorder recorder = null;

        try {
            long start = System.currentTimeMillis();
            log.info("视频存储开始");
            log.info("开始初始化帧抓取器");
            // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
            // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
            grabber.start(true);

            log.info("帧抓取器初始化完成");

            // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中
            AVFormatContext avformatcontext = grabber.getFormatContext();

            // 文件内有几个媒体流（一般是视频流+音频流）
            int streamNum = avformatcontext.nb_streams();

            // 没有媒体流就不用继续了
            if (streamNum < 1) {
                log.info("文件内不存在媒体流");
                throw new Exception("Error! There is no media stream in the file!");
            }

            // 取得视频的帧率
            double framerate = grabber.getVideoFrameRate();

            System.out.printf("视频帧率[%f]，视频时长[%d]秒，媒体流数量[%d]\r\n", framerate, avformatcontext.duration() / 1000000,
                    avformatcontext.nb_streams());

            // 遍历每一个流，检查其类型
            for (int i = 0; i < streamNum; i++) {
                AVStream avstream = avformatcontext.streams(i);
                AVCodecParameters avcodecparameters = avstream.codecpar();
                System.out.printf("流的索引[%d]，编码器类型[%d]，编码器ID[%d]\r\n", i, avcodecparameters.codec_type(), avcodecparameters.codec_id());
            }

            // 视频宽度
            int frameWidth = grabber.getImageWidth();
            // 视频高度
            int frameHeight = grabber.getImageHeight();
            // 音频通道数量
            int audiochannels = grabber.getAudioChannels();

            System.out.printf("视频宽度[%d]，视频高度[%d]，音频通道数[%d]\r\n", frameWidth, frameHeight, audiochannels);

            recorder = new FFmpegFrameRecorder(outputFile, frameWidth, frameHeight, audiochannels);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);

            recorder.setFormat(format);
            // 使用原始视频的码率，若需要则自行修改码率
            recorder.setVideoBitrate(grabber.getVideoBitrate());

            // 一秒内的帧数，帧率
            recorder.setFrameRate(framerate);

            // 两个关键帧之间的帧数
            recorder.setGopSize((int) framerate);

            // 设置音频通道数，与视频源的通道数相等
            recorder.setAudioChannels(grabber.getAudioChannels());

            recorder.start();

            int videoframenum = 0;
            int audioframenum = 0;
            int dataframenum = 0;

            log.info("开始录制\r\n");

            // 循环条件
            int count = 0;

            // 持续从视频源取帧
            while (true) {
                frame = grabber.grab();
                if (frame != null) {
                    // 有图像，就把视频帧加一
                    if (null != frame.image) {
                        videoframenum++;
                        // 取出的每一帧，都保存到视频
                        recorder.record(frame);
                    }
                    // 有声音，就把音频帧加一
                    if (null != frame.samples) {
                        audioframenum++;
                        // 取出的每一帧，都保存到视频
                        recorder.record(frame);
                    }
                    // 有数据，就把数据帧加一
                    if (null != frame.data) {
                        dataframenum++;
                    }
                    count = 0;
                } else {
                    if (retryCount == null || retryCount == 0) {
                        break;
                    }
                    if (count > retryCount) {
                        break;
                    }
                    if (sleepTime != null && sleepTime > 0) {
                        // 休眠一段时间后重试
                        Thread.sleep(sleepTime);
                    }
                    count++;
                }
            }
            long end = System.currentTimeMillis();
            long second = (end - start) / 1000;
            log.info("视频存储结束");
            log.info("视频存储总耗时(秒)：" + second);
            System.out.printf("转码完成，视频帧[%d]，音频帧[%d]，数据帧[%d]\r\n", videoframenum, audioframenum, dataframenum);
            log.info("转码完成，视频帧" + videoframenum + "，音频帧" + audioframenum + "，数据帧" + dataframenum);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (recorder != null) {
                try {
                    recorder.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                grabber.close();
            } catch (FrameGrabber.Exception e) {
                e.printStackTrace();
            }
        }
    }


}
