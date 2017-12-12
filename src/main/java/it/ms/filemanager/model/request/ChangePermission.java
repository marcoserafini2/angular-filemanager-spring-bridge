package it.ms.filemanager.model.request;

import java.io.Serializable;
import java.util.List;

import it.ms.filemanager.model.Mode;

public class ChangePermission extends AbstractParams implements Serializable {

	private static final long serialVersionUID = 1138669741294951453L;

	private List<String> items;

	private String perms;

	private String permsCode;

	private boolean recursive;

	public ChangePermission() {
		super();
	}

	public ChangePermission(List<String> items, String perms, String permsCode, boolean recursive) {
		super();
		this.items = items;
		this.perms = perms;
		this.permsCode = permsCode;
		this.recursive = recursive;
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
	 * @return the perms
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * @param perms
	 *            the perms to set
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * @return the permsCode
	 */
	public String getPermsCode() {
		return permsCode;
	}

	/**
	 * @param permsCode
	 *            the permsCode to set
	 */
	public void setPermsCode(String permsCode) {
		this.permsCode = permsCode;
	}

	/**
	 * @return the recursive
	 */
	public boolean isRecursive() {
		return recursive;
	}

	/**
	 * @param recursive
	 *            the recursive to set
	 */
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	@Override
	protected Mode getAction() {
		return Mode.changePermission;
	}

}
