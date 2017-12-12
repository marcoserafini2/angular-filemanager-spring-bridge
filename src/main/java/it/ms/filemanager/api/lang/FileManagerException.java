package it.ms.filemanager.api.lang;

public class FileManagerException extends RuntimeException {

	private static final long serialVersionUID = -83039863770255940L;

	public FileManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileManagerException(String message) {
		super(message);
	}

}
