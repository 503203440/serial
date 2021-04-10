package com.yx.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocketServer {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(2317);

        while (true) {

            Socket socket = serverSocket.accept();
            SocketServer ss = new SocketServer();
            ss.accept(socket);

        }


    }

    public void accept(Socket socket) throws IOException {
        String hostAddress = socket.getInetAddress().getHostAddress();
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " 连接已建立，hostAddress:\t" + hostAddress);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // 写法1
        byte[] bytes = new byte[1024];
        int length = -1;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
            String s = new String(bytes, 0, length, "UTF-8");
            System.out.printf("%s [%s]: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), hostAddress, s);
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
