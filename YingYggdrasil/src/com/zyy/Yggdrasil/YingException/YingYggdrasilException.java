package com.zyy.Yggdrasil.YingException;

public class YingYggdrasilException {

	private static final long serialVersionUID = 1L;
	
	public static final String m_invalid_token = "Invalid token.";
	public static final String m_invalid_credentials = "Invalid credentials. Invalid username or password.";
	public static final String m_token_already_assigned = "Access token already has a profile assigned.";
	public static final String m_access_denied = "Access denied.";
	public static final String m_profile_not_found = "No such profile.";
	public static final String m_no_credentials = "credentials is null";

	private String error;
	private String message;

	public YingYggdrasilException(int status, String error, String message) {
		this.error = error;
		this.message = message;
	}

	public String getYggdrasilMessage() {
		return message;
	}

	public String getYggdrasilError() {
		return error;
	}
	
	public static YingYggdrasilException newIllegalArgumentException(String message) {
		return new YingYggdrasilException(400, "IllegalArgumentException", message);
	}

	public static YingYggdrasilException newForbiddenOperationException(String message) {
		return new YingYggdrasilException(403, "ForbiddenOperationException", message);
	}
	

}
