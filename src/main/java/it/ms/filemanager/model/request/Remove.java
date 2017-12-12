package it.ms.filemanager.model.request;

import java.io.Serializable;
import java.util.List;

import it.ms.filemanager.model.Mode;

public class Remove extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 1779561161086574855L;

	private List<String> items;

	public Remove() {
		super();
	}

	public Remove(List<String> items, String newPath, String singleFilename) {
		super();
		this.items = items;
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
	 * {@inheritDoc}
	 */
	@Override
	public Mode getAction() {
		return Mode.remove;
	}

}
