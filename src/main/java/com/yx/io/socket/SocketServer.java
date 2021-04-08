package com.yx.io.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);


        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("[client:]" + line);
            printWriter.println(line);
        }

    }


}
