package com.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class NioSelectorServer {

    private static final ArrayList<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        //创建NIO ServerSocketChannel
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(9000));
        //设置非阻塞
        serverSocket.configureBlocking(false);
        //打开Selector处理channel，即创建epoll
        Selector selector = Selector.open();
        //将channel注册到selector上，监听接收事件
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        while (true) {
            //阻塞等待
            selector.select();

            //获取所有事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {//OP_ACCEPT 建立连接事件
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    // TODO: 将新接入的客户端连接注册到selector上，监听读操作
                    if (socketChannel != null) {
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功");
                    }
                } else if (key.isReadable()) {//OP_READ 读取事件
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = socketChannel.read(byteBuffer);
                    if (length > 0) {
                        System.out.println("客户端发送消息：" + new String(byteBuffer.array(), 0, length));
                    } else if (length == -1) {
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
            }
        }
    }

}
