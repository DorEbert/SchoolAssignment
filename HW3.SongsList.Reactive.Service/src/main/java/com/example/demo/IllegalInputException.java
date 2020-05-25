package com.example.demo;

public class IllegalInputException extends RuntimeException {
		private static final long serialVersionUID = -183067629644668501L;

	public IllegalInputException() {
		super();
	}

	public IllegalInputException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalInputException(String message) {
		super(message);
	}

	public IllegalInputException(Throwable cause) {
		super(cause);
	}
}
