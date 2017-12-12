package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class GetList extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 9027465932521353837L;

	private String path;

	public GetList() {
		super();
	}

	public GetList(String path) {
		super();
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Mode getAction() {
		return Mode.list;
	}

}
