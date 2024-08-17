package com.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class NioServer {

    private static final ArrayList<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        //设置非阻塞
        serverSocket.configureBlocking(false);
        System.out.println("服务器启动成功");

        while (true) {
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null && socketChannel.isConnected() && socketChannel.isOpen()) {
                System.out.println("客户端连接成功");
                //设置非阻塞
                socketChannel.configureBlocking(false);
                channelList.add(socketChannel);
            }
            //数据读取
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel channel = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length = channel.read(byteBuffer);
                if (length > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array()));
                } else {
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }

}
