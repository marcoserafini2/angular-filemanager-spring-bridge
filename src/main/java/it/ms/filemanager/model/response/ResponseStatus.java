package it.ms.filemanager.model.response;

import java.io.Serializable;

public class ResponseStatus implements Serializable {

	private static final long serialVersionUID = 1194042470517242682L;

	private boolean success;

	private String error;

	public ResponseStatus() {
		super();
	}

	public ResponseStatus(boolean success) {
		super();
		this.success = success;
	}

	public ResponseStatus(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

}
