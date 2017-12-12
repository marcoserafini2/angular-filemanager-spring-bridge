package it.ms.filemanager.model.request;

import java.io.Serializable;

import it.ms.filemanager.model.Mode;

public class Extract extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 6047997696186615118L;

	private String destination;

	private String item;

	private String folderName;

	public Extract() {
		super();
	}

	public Extract(String destination, String item, String folderName) {
		super();
		this.destination = destination;
		this.item = item;
		this.folderName = folderName;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination
	 *            the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
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
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}

	/**
	 * @param folderName
	 *            the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@Override
	public Mode getAction() {
		return Mode.extract;
	}
}
