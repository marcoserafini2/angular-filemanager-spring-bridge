package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class GetContent extends AbstractParams implements Serializable {

	private static final long serialVersionUID = -7053499191183967384L;

	private String item;

	public GetContent() {
		super();
	}

	public GetContent(String item) {
		super();
		this.item = item;
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

	@Override
	public Mode getAction() {
		return Mode.getContent;
	}

}
