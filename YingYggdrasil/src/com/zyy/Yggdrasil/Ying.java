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

    // �رշ�������
    //private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";


    
    
    
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("ӱ");
        YingLogger.Ying();
        YingLogger.error("ӱ");
        
        System.err.println("˽Կ���ܡ�����Կ����");
        String source = "����һ�в���RSA����ǩ��������������";
        System.out.println("ԭ���֣�\r\n" + source);
		try {
			YingLogger.info(YingRSAUtils.sign(source));
			
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.err.println(e.toString());
		}
		
        Ying server = new Ying();
        //�ȴ���������
        server.await();
        
        
        
        
        
        

    } 
    
    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            //�������׽��ֶ���
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // ѭ���ȴ�һ������
        while (true) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                //�ȴ����ӣ����ӳɹ��󣬷���һ��Socket����
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // ����Request���󲢽���
                Request request = new Request(input);
                request.parse();
                

                // ���� Response ����
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                // �ر� socket ����
                socket.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        
        
    }
   
}
