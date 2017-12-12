package it.ms.filemanager.model.response;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {

	private static final long serialVersionUID = 8810017038211492109L;

	private T result;

	public ResponseModel(T result) {
		super();
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}

}
