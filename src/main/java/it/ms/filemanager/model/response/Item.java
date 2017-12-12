package it.ms.filemanager.model.response;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {

	private static final long serialVersionUID = 6132406001754592219L;

	public enum Type {
		dir, file
	}

	private String name;

	private String rights;

	private Date date;

	private long size;

	private Type type;


	public Item() {
		super();
	}

	/**
	 *
	 * @param name
	 * @param rights
	 * @param date
	 * @param size
	 * @param type
	 */
	public Item(String name, String rights, Date date, long size, Type type) {
		super();
		this.name = name;
		this.rights = rights;
		this.date = date;
		this.size = size;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * @param rights
	 *            the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

}
