package it.ms.filemanager.model.response;

import java.io.Serializable;

public class SuccessfulResponse extends ResponseModel<ResponseStatus> implements Serializable {

	private static final long serialVersionUID = 662939377237365041L;

	public SuccessfulResponse() {
		super(new ResponseStatus(true));
	}

	public SuccessfulResponse(String message) {
		super(new ResponseStatus(true, message));
	}
}
