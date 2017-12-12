package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class Rename extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 7549792024793358523L;

	private String item;

	private String newItemPath;

	public Rename() {
		super();
	}

	public Rename(String item, String newItemPath) {
		super();
		this.item = item;
		this.newItemPath = newItemPath;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the newItemPath
	 */
	public String getNewItemPath() {
		return newItemPath;
	}

	/**
	 * @param newItemPath
	 *            the newItemPath to set
	 */
	public void setNewItemPath(String newItemPath) {
		this.newItemPath = newItemPath;
	}

	@Override
	public Mode getAction() {
		return Mode.rename;
	}

}
