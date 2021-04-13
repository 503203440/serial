package com.yx.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {


    public static void main(String[] args) throws IOException {

        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);

        ServerSocket serverSocket = new ServerSocket(2317);

        while (true) {

            Socket socket = serverSocket.accept();
            executorService.submit(new Thread(() -> {
                SocketServer socketServer = new SocketServer();
                try {
                    socketServer.accept(socket);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }));

        }


    }

    public void accept(Socket socket) throws IOException {
        String threadName = Thread.currentThread().getName();
        String hostAddress = socket.getInetAddress().getHostAddress();
        System.out.println(threadName + "   " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " 连接已建立，hostAddress:\t" + hostAddress);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // 写法1
        byte[] bytes = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
            String s = new String(bytes, 0, length, "UTF-8");
            System.out.printf("%s   %s [%s]: %s\n", threadName, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), hostAddress, s);
        }

        // 写法2
//        byte[] bytes = new byte[1024];
//        int length = 0;
//        while ((length = inputStream.read(bytes)) != -1) {
//            // 输出byteArrayOutputStream的内容
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            byteArrayOutputStream.write(bytes, 0, length);
//            byte[] byteArray = byteArrayOutputStream.toByteArray();
//            String string = byteArrayOutputStream.toString("UTF-8");
//            System.out.println("client:" + string);
//            // 输出字节流
//            outputStream.write(bytes, 0, length);
//        }


    }


}
