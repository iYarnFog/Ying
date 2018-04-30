package com.zyy.Yggdrasil;

import java.io.OutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.zyy.YingLogger;
import com.zyy.Yggdrasil.YingYggdrasil;
import com.zyy.YingUtils.YingRSAUtils;
import com.zyy.YingUtils.YingUUIDUtils;

/*
  HTTP Response = Status-Line
    *(( general-header | response-header | entity-header ) CRLF)
    CRLF
    [ message-body ]
    Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
*/
enum YStatus {
	/*
		一般HTTP异常（非业务异常，如 Not Found、 Method Not Allowed）	未定义	该HTTP状态对应的Reason Phrase（于HTTP/1.1中定义）	未定义
		令牌无效												403		ForbiddenOperationException					Invalid token.
		密码错误，或短时间内多次登录失败而被暂时禁止登录					403		ForbiddenOperationException					Invalid credentials. Invalid username or password.
		试图向一个已经绑定了角色的令牌指定其要绑定的角色					400		IllegalArgumentException					Access token already has a profile assigned.
		试图向一个令牌绑定不属于其对应用户的角色 （非标准）					403		ForbiddenOperationException					未定义
		试图使用一个错误的角色加入服务器								403		ForbiddenOperationException					Invalid token.
	*/
	spring, winter;
	public int YCode(YStatus Y) {
		return 1;
	}
	public String YMessage(YStatus Y) {
		return null;
	}
}

public class Response {
    Request request;
    OutputStream output;
    
    private static final YingYggdrasil Y = new YingYggdrasil();

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @SuppressWarnings("unchecked")
	public void sendStaticResource() throws Exception {
        YingLogger.info(request.getUri());
        
        
       
        
        
        if(request.getUri().equals("/")) {
        	
        	JSONObject YJson = new JSONObject();
        	HashMap<String, String> YMeta = new HashMap<String, String>();
        	YMeta.put("serverName", "Ying");
        	YMeta.put("implementationName", "YingAir");
        	YMeta.put("implementationVersion", "6.0.4");
        	YJson.put("meta", YMeta);
        	List YskinDomains = new ArrayList();
        	YskinDomains.add(".zyy.com");
        	YJson.put("skinDomains", YskinDomains);
        	YJson.put("signaturePublickey", Y.YingPublic);
        	output.write(YResponseBuilder(200, YJson.toJSONString()).getBytes("utf-8"));
        	
        } else if(request.getUri().equals("/authserver/authenticate")) {
        	
        	JSONParser YParser = new JSONParser();
        	JSONObject YUserInfo = null;
        	
            try {
         	   YUserInfo = (JSONObject)YParser.parse(request.getContent());       	   
            } catch (ParseException e) {
            	output.write(YResponseBuilder(500, "").getBytes("utf-8"));
     			// TODO 自动生成的 catch 块
         	   e.printStackTrace();
            }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
            
            
            
        	if(Y.Authenticate(YUserInfo)) {
        		
        		YingUserInfo YingUser = YingYggdrasil.YUsers.get(YUserInfo.get("clientToken"));
        		
        		JSONObject YJson = new JSONObject();
        		YJson.put("accessToken", YingUUIDUtils.toUUID(YingUUIDUtils.randomUnsignedUUID()).toString());
        		YJson.put("clientToken", YingUser.getClientToken());
        		
        		JSONArray YAvailableProfiles = new JSONArray();
        		Map<String, String> YAvailableProfile;
        		YAvailableProfile = new HashMap<String, String>() ;
        		YAvailableProfile.put("id", "d3af753b7cda4666adc2ff9bba85e0eb");
        		YAvailableProfile.put("name", "Ying");
        		YAvailableProfiles.add(YAvailableProfile);
        		YJson.put("availableProfiles", YAvailableProfiles);
        		
        		Map<String, String> YSelectedProfile = new HashMap<String, String>() ;
        		YSelectedProfile.put("id", "d3af753b7cda4666adc2ff9bba85e0eb");
        		YSelectedProfile.put("name", "Ying");
        		YJson.put("selectedProfile", YSelectedProfile);
        		
        		Map<String, Object> YUser = new HashMap<String, Object>() ;
        		YUser.put("id", "d3af753b7cda4666adc2ff9bba85e0eb");
        		JSONArray YProperties = new JSONArray();
        		YUser.put("properties", YProperties);
        		
        		YJson.put("user", YUser);
        		
            	output.write(YResponseBuilder(200, YJson.toJSONString()).getBytes("utf-8"));
        	} else {
        		JSONObject YJson = new JSONObject();
        		YJson.put("error", "403");
        		YJson.put("errorMessage", "Invalid credentials. Invalid username or password.");
        		output.write(YResponseBuilder(403, YJson.toJSONString()).getBytes("utf-8"));
        	}
        	
        } else if(request.getUri().equals("/authserver/refresh")) {
        	JSONObject YJson = new JSONObject();
    		YJson.put("accessToken", YingUUIDUtils.toUUID(YingUUIDUtils.randomUnsignedUUID()).toString());
    		YJson.put("clientToken", "c8d53327-5e7b-491a-a3d6-6b612d4cd9ab");
    		
    		
    		Map<String, String> YSelectedProfile = new HashMap<String, String>() ;
    		YSelectedProfile.put("id", "d3af753b7cda4666adc2ff9bba85e0eb");
    		YSelectedProfile.put("name", "Ying");
    		YJson.put("selectedProfile", YSelectedProfile);
    		
    		Map<String, Object> YUser = new HashMap<String, Object>();
    		YUser.put("id", "d3af753b7cda4666adc2ff9bba85e0eb");
    		JSONArray YProperties = new JSONArray();
    		YUser.put("properties", YProperties);
    		
    		YJson.put("user", YUser);
    		
        	output.write(YResponseBuilder(200, YJson.toJSONString()).getBytes("utf-8"));
        } else if(request.getUri().indexOf("/sessionserver/session/minecraft/profile/") != -1) {
        	
        	int YBegin = request.getUri().indexOf("/profile/") + 2;
        	int YEnd = request.getUri().indexOf("?");
        	String[] YUserUUID = request.getUri().substring(YBegin, YEnd).replace("/profile/", "").split("/");
        	YingLogger.info(YUserUUID[1]);
        	
        	JSONObject YJson = new JSONObject();
        	YJson.put("id", YUserUUID[1]);
        	YJson.put("name", "Ying");
        	JSONArray YProperties = new JSONArray();
        	Map<String, String> YPropertie = new HashMap<String, String>() ;
        	YPropertie.put("name", "textures");
        	
        	JSONObject YSkin = new JSONObject();
        	YSkin.put("timestamp", "1502200990028");
        	YSkin.put("profileId", YingUUIDUtils.toUUID(YUserUUID[1]));
        	YSkin.put("profileName", "Ying");
        	YSkin.put("isPublic", true);
        	
        	YPropertie.put("value", "");
        	if (request.YGet("unsigned").equals("true")) {
        		YPropertie.put("signature", YingRSAUtils.sign(""));
			}
        	YProperties.add(YPropertie);
        	
        	output.write(YResponseBuilder(200, "eyJ0aW1lc3RhbXAiOiAxNTAyMjAwOTkwMDI4LCJwcm9maWxlSWQiOiAiZDNhZjc1M2ItN2NkYS00NjY2LWFkYzItZmY5YmJhODVlMGViIiwicHJvZmlsZU5hbWUiOiAiWWluZyIsImlzUHVibGljIjogdHJ1ZSwidGV4dHVyZXMiOiB7IlNLSU4iOiB7InVybCI6ICJodHRwOi8vd3d3Lnp5eS5jb20vdGV4dHVyZXMvOGRlMGVlYzVhNTY3NmNkZjI5NjdhNzMzZGIyZjBmMTZjMzRmNzY3Y2RhODE5YTBlMTE2YWQxYjkzNjkwNGYifSwiQ0FQRSI6IHsidXJsIjogImh0dHA6Ly93d3cuenl5LmNvbS90ZXh0dXJlcy84ZGUwZWVjNWE1Njc2Y2RmMjk2N2E3MzNkYjJmMGYxNmMzNGY3NjdjZGE4MTlhMGUxMTZhZDFiOTM2OTA0ZiJ9fSwic2lnbmF0dXJlUmVxdWlyZWQiOiB0cnVlfQ==").getBytes("utf-8"));
        	/*
        		{
        		  "id": "d3af753b7cda4666adc2ff9bba85e0eb",
        		  "name": "621sama",
        		  "properties": [
        		    {
        		      "name": "textures",
        		      "value": "eyJ0aW1lc3RhbXAiOjE1MDIyMDA5OTAwMjgsInByb2ZpbGVJZCI6ImQzYWY3NTNiLTdjZGEtNDY2Ni1hZGMyLWZmOWJiYTg1ZTBlYiIsInByb2ZpbGVOYW1lIjoiNjIxc2FtYSIsImlzUHVibGljIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly9za2luLmRldi90ZXh0dXJlcy84MzRjYmQ4NDhmMGEyOTAwOGJmNWIxZDU5ZDAyZWNiMWNmMjVkZmQyMWZjODhiZTFjMTgzYzkyNjFmNWZkZDY5In0sIkNBUEUiOnsidXJsIjoiaHR0cDovL3NraW4uZGV2L3RleHR1cmVzLzI5MTE0MzhlODI4MmQ0MGU2ZDY0ZmJlZmQwNzZlZWYwYTkwMWNiOTBkM2RlYWU0MDU3ZmVjNjBjNjZlYjkzZDIifX0sInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlfQ==",
        		      "signature": "Zvox4YClUMHIAMe1tRLV/JmMaGF0pZhkmrigFpo7jOme8f559gZVyBQoTXeZsXn7Hwq5TE0b9m09MzuAGoT7dQ7kxkHA60xvVQXMQlbWP5O+EA8fzOM0hgINe8Qv7hSBG89osr+wWE7pTJ1CIKD6CBoK1a/U9UiCyQuDlO2gnfnXebBDIXJCBMKiowTu1LubZ9EQn7WkgrFD/M7TY+2dr8DOdoq15Pv0EZ2kLO1Gu9y6vOPq+5nAhce/TN/sWGAvfCJJkSYqALBSFh7QkExTJXPM7QHgP++rn96m6/nDe/ND6NwEovwdVqD5KiPnTvzRLkr92QEdZniT6hH2DUrToA=="
        		    }
        		  ]
        		}
        	 */
        } else {
        	
	        String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
	                + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
	        output.write(errorMessage.getBytes());
        }
        
        /*
        try {
            //将web文件写入到OutputStream字节流中
            File file = new File(Ying.WEB_ROOT, request.getUri());
            System.out.println(Ying.WEB_ROOT + request.getUri());
            if (file.exists()) {       
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);        
                while (ch != -1) {
                    output.write(bytes, 0, ch);                  
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n"
                        + "Content-Length: 23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
                output.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString());
        } finally {
            if (fis != null)
                fis.close();
        }
        */
    }
    
    public String YResponseBuilder(int YStatus, String YContent) {
    	StringBuilder Y = new StringBuilder();
    	Y.append("HTTP/1.1 " + YStatus + " Ying\r\n");
    	Y.append("Content-Type: application/json;charset=UTF-8\r\n");
    	Y.append("Content-Length: " + getLength(YContent) + "\r\n" + "\r\n");
    	Y.append(YContent);
    	return Y.toString();
    }
    
    public static int getLength(String YContent) {
    	int valueLength = 0;  
        String chinese = "[\u4e00-\u9fa5]";  
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为3，否则为1  
        for (int i = 0; i < YContent.length(); i++) {  
            // 获取一个字符  
            String temp = YContent.substring(i, i + 1);  
            // 判断是否为中文字符  
            if (temp.matches(chinese)) {  
                // 中文字符长度为3  
                valueLength += 3;  
            } else {  
                // 其他字符长度为1 
                valueLength += 1;  
            }  
        }  
        //进位取整  
        return valueLength;  
    }

    
}