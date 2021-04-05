package com.yx.io;

public class Main {

    public static void main(String[] args) {
        // 实例化串口操作类对象
        SerialPortUtils serialPort = new SerialPortUtils();
        // 创建串口必要参数接收类并赋值，赋值串口号，波特率，校验位，数据位，停止位
        ParamConfig paramConfig = new ParamConfig("COM5", 115200, 0, 8, 1);
//        ParamConfig paramConfig = new ParamConfig("COM5", 9600, 0, 8, 1);
        // 初始化设置,打开串口，开始监听读取串口数据
        serialPort.init(paramConfig);

        serialPort.readComm();
        String data = serialPort.getData();
        System.out.println(data);

        // 调用串口操作类的sendComm方法发送数据到串口
//        serialPort.sendComm("FEF10A000000000000000AFF");
        // 关闭串口
        serialPort.closeSerialPort();
    }

}
