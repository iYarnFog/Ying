package com.zyy.Yggdrasil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

import com.zyy.YingLogger;
import com.zyy.YingUtils.YingRSAUtils;



public class Ying {

	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    // 关闭服务命令
    //private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";


    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("颖");
        YingLogger.Ying();
        YingLogger.error("颖");
        
        System.err.println("私钥加密——公钥解密");
        String source = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("原文字：\r\n" + source);
		try {
			YingLogger.info(YingRSAUtils.sign(source));
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.err.println(e.toString());
		}
		
        Ying server = new Ying();
        //等待连接请求
        server.await();
        
        
        
        
        
        

    } 
    
    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            //服务器套接字对象
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // 循环等待一个请求
        while (true) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                //等待连接，连接成功后，返回一个Socket对象
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // 创建Request对象并解析
                Request request = new Request(input);
                request.parse();
                

                // 创建 Response 对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // 关闭 socket 对象
                socket.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        
        
    }
   
}
