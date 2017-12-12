package it.ms.filemanager.model.request;

import java.io.Serializable;
import java.util.List;

import it.ms.filemanager.model.Mode;

public class Copy extends AbstractParams implements Serializable {

	private static final long serialVersionUID = -3254468874811728423L;

	private List<String> items;

	private String newPath;

	// <-- (only present in single selection copy)
	private String singleFilename;

	public Copy() {
		super();
	}

	public Copy(List<String> items, String newPath, String singleFilename) {
		super();
		this.items = items;
		this.newPath = newPath;
		this.singleFilename = singleFilename;
	}

	/**
	 * @return the items
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<String> items) {
		this.items = items;
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

	/**
	 * @return the singleFilename
	 */
	public String getSingleFilename() {
		return singleFilename;
	}

	/**
	 * @param singleFilename
	 *            the singleFilename to set
	 */
	public void setSingleFilename(String singleFilename) {
		this.singleFilename = singleFilename;
	}

	@Override
	public Mode getAction() {
		return Mode.copy;
	}

}
