package com.zyy.Yggdrasil;

import java.util.Map;

public class YingUserInfo {
	
	private String username; //����
	private String password; //����
	private String clientToken; //�ɿͻ���ָ�������Ƶ�clientToken(��ѡ)
	private boolean requestUser; //�Ƿ�����Ӧ�а����û���Ϣ��Ĭ��false
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
