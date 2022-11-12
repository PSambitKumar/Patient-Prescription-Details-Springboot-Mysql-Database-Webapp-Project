package com.csm.Model;

/**
 * @Project : PatientInformation
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 12/11/2022 - 6:12 PM
 */
public class Response {
	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response{" +
			   "status='" + status + '\'' +
			   ", message='" + message + '\'' +
			   '}';
	}
}
