package com.example.demo.payload;

public class ApiResponse<T> {

	private String Success;

	private String message;

	private String Status;

	private T data;

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(String success, String message, String status, T data) {
		super();
		Success = success;
		this.message = message;
		Status = status;
		this.data = data;
	}

	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResoponse [Success=" + Success + ", message=" + message + ", Status=" + Status + ", data=" + data
				+ "]";
	}

}
