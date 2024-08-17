package com.demo.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    // 创建线程池处理客户端请求
    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接");
            Socket clientSocket = serverSocket.accept();
            System.out.println("建立客户端连接，准备读取数据");
            //executorService.submit(() -> {
                try {
                    handle(clientSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            //});
        }
    }

    private static void handle(Socket clientSocket) throws IOException {
        if (clientSocket == null || !clientSocket.isConnected() || clientSocket.isClosed()) {
            return;
        }
        // 处理客户端请求
        byte[] bytes = new byte[1024];
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("读取完毕:" + read);
        String str = new String(bytes, 0, read, StandardCharsets.UTF_8);
        System.out.println("客户端发送的数据：" + str);
    }

}
