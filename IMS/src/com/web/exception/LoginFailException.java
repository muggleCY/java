package com.web.exception;

public class LoginFailException extends Exception{

	public LoginFailException() {
		super();
	}

	public LoginFailException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public LoginFailException(String arg0) {
		super(arg0);
	}

	public LoginFailException(Throwable arg0) {
		super(arg0);
	}
	
}
