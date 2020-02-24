package com.jc.exception;

public class GameTypeExistException extends Exception {

	public GameTypeExistException() {
		super();
	}

	public GameTypeExistException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public GameTypeExistException(String arg0) {
		super(arg0);
	}

	public GameTypeExistException(Throwable arg0) {
		super(arg0);
	}

}
