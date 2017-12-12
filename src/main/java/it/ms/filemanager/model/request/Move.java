package it.ms.filemanager.model.request;

import java.io.Serializable;
import java.util.List;

import it.ms.filemanager.model.Mode;

public class Move extends AbstractParams implements Serializable {

	private static final long serialVersionUID = -4952000663281894189L;

	private List<String> items;

	private String newPath;

	public Move() {
		super();
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

	@Override
	protected Mode getAction() {
		return Mode.move;
	}

}
