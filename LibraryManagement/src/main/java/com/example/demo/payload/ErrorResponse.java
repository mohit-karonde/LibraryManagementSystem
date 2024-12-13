package com.example.demo.payload;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private String Status;
	
	private String errormessage;
	
	private String  message;
	
	
    private LocalDateTime timestamp;
	
    
    
    
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}





	public ErrorResponse(String status, String errormessage, String message ) {
		super();
		Status = status;
		this.errormessage = errormessage;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}





	public String getStatus() {
		return Status;
	}





	public void setStatus(String status) {
		Status = status;
	}





	public String getErrormessage() {
		return errormessage;
	}





	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}





	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}





	public LocalDateTime getTimestamp() {
		return timestamp;
	}





	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}





	@Override
	public String toString() {
		return "ErrorResponse [Status=" + Status + ", errormessage=" + errormessage + ", message=" + message
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
	
	
	
	
	
}

