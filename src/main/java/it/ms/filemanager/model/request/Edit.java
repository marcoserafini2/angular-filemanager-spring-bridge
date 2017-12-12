package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class Edit extends AbstractParams implements Serializable {

	private static final long serialVersionUID = -1702131221260325942L;

	private String item;

	private String content;

	public Edit() {
		super();
	}

	public Edit(String item, String content) {
		super();
		this.item = item;
		this.content = content;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Mode getAction() {
		return Mode.edit;
	}

}
