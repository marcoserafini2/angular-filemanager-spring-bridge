package it.ms.filemanager.model.response;

import java.io.Serializable;

public class ErrorResponse extends ResponseModel<ResponseStatus> implements Serializable {

	private static final long serialVersionUID = -5397343449969224817L;

	public ErrorResponse(String error) {
		super(new ResponseStatus(false, error));
	}

}
