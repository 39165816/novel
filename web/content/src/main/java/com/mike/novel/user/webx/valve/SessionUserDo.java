package com.mike.novel.user.webx.valve;

import java.io.Serializable;

public class SessionUserDo implements Serializable {

	private static final long serialVersionUID = -9014627584921958673L;

	public SessionUserDo() {

	}

	public SessionUserDo(String userName) {
		this.userName = userName;
	}

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "[" + userName + "]";
	}
}