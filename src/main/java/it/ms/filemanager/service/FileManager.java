package it.ms.filemanager.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.ms.filemanager.model.response.Item;

public interface FileManager {

	/**
	 * List
	 *
	 * @param path
	 * @return
	 */
	List<Item> list(String path);

	/**
	 * Rename
	 *
	 * @param item
	 * @param newItemPath
	 * @return
	 */
	boolean rename(String item, String newItemPath);

	/**
	 * Move
	 *
	 * @param items
	 * @param newItemPath
	 * @return
	 */
	boolean move(List<String> items, String newItemPath);

	/**
	 * Copy
	 *
	 * @param items
	 * @param newPath
	 * @param newFileName
	 * @return
	 */
	boolean copy(List<String> items, String newPath, String newFileName);

	/**
	 * Remove
	 * @param list
	 * @return
	 */
	int remove(List<String> list);

	/**
	 * Edit
	 *
	 * @param item
	 * @param content
	 * @return
	 */
	boolean edit(String item, String content);

	/**
	 * Get Content
	 *
	 * @param string
	 * @return
	 */
	String getContent(String string);

	/**
	 * Change Permission
	 *
	 * @param items
	 * @param perms
	 * @param permsCode
	 * @param recursive
	 * @return
	 */
	boolean changePermission(List<String> items, String perms, String permsCode, boolean recursive);

	/**
	 * Create folder
	 *
	 * @param string
	 * @return
	 */
	boolean createFolder(String string);

	/**
	 * Compress
	 *
	 * @param items
	 * @param destination
	 * @param compressedFilename
	 * @return
	 */
	boolean compress(List<String> items, String destination, String compressedFilename);

	/**
	 * Extract
	 *
	 * @param zip
	 * @param destination
	 * @param folderName
	 * @return
	 */
	boolean extract(String zip, String destination, String folderName);


	/**
	 * Store
	 * @param multipartFile
	 * @param newItemPath
	 * @return
	 */
	boolean store(MultipartFile multipartFile, String newItemPath);

	/**
	 * Get File
	 * @return
	 */
	File getFile(String path);

}
