package com.yx.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

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
        System.out.println("连接已建立，hostAddress:\t" + hostAddress);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, length);
        }


    }


}
