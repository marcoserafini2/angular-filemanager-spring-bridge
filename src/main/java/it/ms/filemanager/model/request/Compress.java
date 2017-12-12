package it.ms.filemanager.model.request;

import java.io.Serializable;
import java.util.List;

import it.ms.filemanager.model.Mode;

public class Compress extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 4885864919306125075L;

	private List<String> items;

	private String destination;

	private String compressedFilename;

	public Compress() {
		super();
	}

	public Compress(List<String> items, String destination, String compressedFilename) {
		super();
		this.items = items;
		this.destination = destination;
		this.compressedFilename = compressedFilename;
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
	 * @return the compressedFilename
	 */
	public String getCompressedFilename() {
		return compressedFilename;
	}

	/**
	 * @param compressedFilename
	 *            the compressedFilename to set
	 */
	public void setCompressedFilename(String compressedFilename) {
		this.compressedFilename = compressedFilename;
	}

	@Override
	public Mode getAction() {
		return Mode.compress;
	}

}