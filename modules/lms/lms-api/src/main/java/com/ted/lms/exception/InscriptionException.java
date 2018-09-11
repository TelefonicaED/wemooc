package com.ted.lms.exception;

public class InscriptionException extends Exception {
	
	private String key;
	
	public InscriptionException(String key, String message ) {
		super(message);
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
