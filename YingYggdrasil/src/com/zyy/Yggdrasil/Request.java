package com.zyy.Yggdrasil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.zyy.YingLogger;

import java.io.IOException;


public class Request {

    private InputStream input;
    private String YRequest;

    public Request(InputStream input) {
        this.input = input;
    }

    //从InputStream中读取request信息，并从request中获取uri值
    public void parse() throws IOException {
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = input.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        YingLogger.info(request.toString());
        
        YRequest = request.toString();
        /*
        Map<String, String> YMap = (Map<String, String>)YGet(null);
        YingLogger.info(YMap.get("Ying"));
        
        YingLogger.info(YGet("Ying").toString());
        */
        
    }

    /**
     * 
     * requestString形式如下：
     * GET /index.html HTTP/1.1
     * Host: localhost:8080
     * Connection: keep-alive
     * Cache-Control: max-age=0
     * ...
     * 该函数目的就是为了获取/index.html字符串
     */
    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }

    public String getUri() {
        return parseUri(YRequest);
    }
    
    public Object YGet(String YArg) {
    	Map<String, String> YMap = new HashMap<String, String>();
    	int YBegin = YRequest.indexOf("/?") + 2;
    	int YEnd = YRequest.indexOf("HTTP/");
    	String YCondtion = YRequest.substring(YBegin, YEnd);
    	String[] Y = YCondtion.split("\\?")[1].split("&");
    	for(int i = 0; i < Y.length; i++) {
    		String[] YY = Y[i].split("=");
    		YMap.put(YY[0], YY[1]);
    		System.out.println(YY[0] + "颖" + YY[1]);
    	}
    	if(YArg == null) {
    		return YMap;
    	} else {
    		try {
    			return YMap.get(YArg);
    		} catch (ArrayIndexOutOfBoundsException e) {
    			return null;
    		}
    	}
    }
    
    public String getContent() {
    	String[] Y = YRequest.split("\r\n");
    	System.out.println(Y[Y.length - 1]);
    	return Y[Y.length - 1];
    }

}