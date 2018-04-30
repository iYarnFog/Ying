package com.zyy.Yggdrasil;

import java.util.Map;

public class YingUserInfo {
	
	private String username; //邮箱
	private String password; //密码
	private String clientToken; //由客户端指定的令牌的clientToken(可选)
	private boolean requestUser; //是否在响应中包含用户信息，默认false
	private Map<String, String> agent;
	
	public void setUsername(String YUsername) {
		this.username = YUsername;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String YPassword) {
		this.password = YPassword;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setClientToken(String YClientToken) {
		this.clientToken = YClientToken;
	}
	
	public String getClientToken() {
		return clientToken;
	}
	
	public void setRequestUser(boolean YRequestUser) {
		this.requestUser = YRequestUser;
	}
	
	public boolean getRequestUser() {
		return requestUser;
	}

	public void setAgent(Map<String, String> YAgent) {
		this.agent = YAgent;
	}
	

	public Map<String, String> getAgent() {
		return agent;
	}

	
}
