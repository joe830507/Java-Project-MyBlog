package com.lin.responseCodeEnum;

import java.util.Map;

public enum ResponseCode {

	REQUEST_SUCCESS(100, "Request succeed!"), LOGIN_FAILED(200, "Login failed!"),
	ADD_USER_FAILED(300, "Failed to add user!"), DUPLICATED_LOGIN(400, "You're kicked out! Duplicated Login!");

	private Integer resCode;

	private String resMsg;

	ResponseCode(Integer resCode, String resMsg) {
		this.resCode = resCode;
		this.resMsg = resMsg;
	}

	public void setResponseInfo(Map<String, Object> response) {
		response.put("resCode", resCode);
		response.put("resMsg", resMsg);
	}
}
