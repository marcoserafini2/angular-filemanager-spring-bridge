package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class CreateFolder extends AbstractParams implements Serializable {

	private static final long serialVersionUID = -815504840303622261L;

	private String newPath;

	public CreateFolder() {
		super();
	}

	public CreateFolder(String newPath) {
		super();
		this.newPath = newPath;
	}

	/**
	 * @return the newPath
	 */
	public String getNewPath() {
		return newPath;
	}

	/**
	 * @param newPath
	 *            the newPath to set
	 */
	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	@Override
	public Mode getAction() {
		return Mode.createFolder;
	}

}
