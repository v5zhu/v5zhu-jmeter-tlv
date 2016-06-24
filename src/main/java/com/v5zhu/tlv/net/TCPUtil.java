package com.v5zhu.tlv.net;

import com.v5zhu.tlv.util.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TCPUtil{

    /**
     * 发送TCP请求
     *
     * @param IP      远程主机地址
     * @param port    远程主机端口
     * @param reqData 待发送报文的中文字符串形式
     * @return localPort--本地绑定的端口,reqData--请求报文,respData--响应报文
     */
    public synchronized static Map<String, String> sendTCPRequest(String IP, int port, byte[] reqData) {
        Map<String, String> respMap = new HashMap<>();
        OutputStream out;      //写
        InputStream in;        //读
        String respData = null;       //响应报文
        Socket socket = new Socket(); //客户机
        try {
            socket.setTcpNoDelay(true);
            socket.setReuseAddress(true);
            socket.setSoTimeout(60000);
            socket.setSoLinger(true, 5);
            socket.setSendBufferSize(1024);
            socket.setReceiveBufferSize(1024);
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress(IP, port), 60000);
            System.out.println(socket.isConnected());
            /**
             * 发送HTTP请求
             */
            out = socket.getOutputStream();
            out.write(reqData);
            /**
             * 接收HTTP响应
             */
            in = socket.getInputStream();
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int len = -1;
            len = in.read(buffer);
            bytesOut.write(buffer, 0, len);
            /**
             * 解码HTTP响应的完整报文
             */
            respData = ByteUtils.bytes2HexString(bytesOut.toByteArray()).toLowerCase();
        } catch (IOException e) {
            respData="timeout";
            System.out.println("与[" + IP + ":" + port + "]通信遇到异常,堆栈信息如下");
            e.printStackTrace();
        } finally {
            if (null != socket && socket.isConnected() && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("关闭客户机Socket时发生异常,堆栈信息如下");
                    e.printStackTrace();
                }
            }
        }
        respMap.put("respData", respData);
        return respMap;
    }

}
