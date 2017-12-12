package it.ms.filemanager.configuration;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.filemanager")
public class ConfigFileManager implements Serializable {

	private static final long serialVersionUID = -3772778773231286304L;

	private String repoBasePath;

	private String basePath;

	private boolean onlyFolders;

	private final AllowedAction allowedActions = new AllowedAction();

	/**
	 * @return the repoBasePath
	 */
	public String getRepoBasePath() {
		return repoBasePath;
	}

	/**
	 * @param repoBasePath
	 *            the repoBasePath to set
	 */
	public void setRepoBasePath(String repoBasePath) {
		this.repoBasePath = repoBasePath;
	}

	/**
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath
	 *            the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * @return the onlyFolders
	 */
	public boolean isOnlyFolders() {
		return onlyFolders;
	}

	/**
	 * @param onlyFolders
	 *            the onlyFolders to set
	 */
	public void setOnlyFolders(boolean onlyFolders) {
		this.onlyFolders = onlyFolders;
	}

	/**
	 * @return the allowedActions
	 */
	public AllowedAction getAllowedActions() {
		return allowedActions;
	}

	public static class AllowedAction implements Serializable {

		private static final long serialVersionUID = -3297039605754969184L;

		private boolean upload;

		private boolean rename;

		private boolean move;

		private boolean copy;

		private boolean edit;

		private boolean changePermissions;

		private boolean compress;

		private boolean compressChooseName;

		private boolean extract;

		private boolean download;

		private boolean downloadMultiple;

		private boolean preview;

		private boolean remove;

		private boolean createFolder;

		private boolean pickFiles;

		private boolean pickFolders;

		/**
		 * @return the upload
		 */
		public boolean isUpload() {
			return upload;
		}

		/**
		 * @param upload
		 *            the upload to set
		 */
		public void setUpload(boolean upload) {
			this.upload = upload;
		}

		/**
		 * @return the rename
		 */
		public boolean isRename() {
			return rename;
		}

		/**
		 * @param rename
		 *            the rename to set
		 */
		public void setRename(boolean rename) {
			this.rename = rename;
		}

		/**
		 * @return the move
		 */
		public boolean isMove() {
			return move;
		}

		/**
		 * @param move
		 *            the move to set
		 */
		public void setMove(boolean move) {
			this.move = move;
		}

		/**
		 * @return the copy
		 */
		public boolean isCopy() {
			return copy;
		}

		/**
		 * @param copy
		 *            the copy to set
		 */
		public void setCopy(boolean copy) {
			this.copy = copy;
		}

		/**
		 * @return the edit
		 */
		public boolean isEdit() {
			return edit;
		}

		/**
		 * @param edit
		 *            the edit to set
		 */
		public void setEdit(boolean edit) {
			this.edit = edit;
		}

		/**
		 * @return the changePermission
		 */
		public boolean isChangePermissions() {
			return changePermissions;
		}

		/**
		 * @param changePermission
		 *            the changePermission to set
		 */
		public void setChangePermissions(boolean changePermissions) {
			this.changePermissions = changePermissions;
		}

		/**
		 * @return the compress
		 */
		public boolean isCompress() {
			return compress;
		}

		/**
		 * @param compress
		 *            the compress to set
		 */
		public void setCompress(boolean compress) {
			this.compress = compress;
		}

		/**
		 * @return the compressChooseName
		 */
		public boolean isCompressChooseName() {
			return compressChooseName;
		}

		/**
		 * @param compressChooseName
		 *            the compressChooseName to set
		 */
		public void setCompressChooseName(boolean compressChooseName) {
			this.compressChooseName = compressChooseName;
		}

		/**
		 * @return the extract
		 */
		public boolean isExtract() {
			return extract;
		}

		/**
		 * @param extract
		 *            the extract to set
		 */
		public void setExtract(boolean extract) {
			this.extract = extract;
		}

		/**
		 * @return the download
		 */
		public boolean isDownload() {
			return download;
		}

		/**
		 * @param download
		 *            the download to set
		 */
		public void setDownload(boolean download) {
			this.download = download;
		}

		/**
		 * @return the downloadMultiple
		 */
		public boolean isDownloadMultiple() {
			return downloadMultiple;
		}

		/**
		 * @param downloadMultiple
		 *            the downloadMultiple to set
		 */
		public void setDownloadMultiple(boolean downloadMultiple) {
			this.downloadMultiple = downloadMultiple;
		}

		/**
		 * @return the preview
		 */
		public boolean isPreview() {
			return preview;
		}

		/**
		 * @param preview
		 *            the preview to set
		 */
		public void setPreview(boolean preview) {
			this.preview = preview;
		}

		/**
		 * @return the remove
		 */
		public boolean isRemove() {
			return remove;
		}

		/**
		 * @param remove
		 *            the remove to set
		 */
		public void setRemove(boolean remove) {
			this.remove = remove;
		}

		/**
		 * @return the createFolder
		 */
		public boolean isCreateFolder() {
			return createFolder;
		}

		/**
		 * @param createFolder
		 *            the createFolder to set
		 */
		public void setCreateFolder(boolean createFolder) {
			this.createFolder = createFolder;
		}

		/**
		 * @return the pickFiles
		 */
		public boolean isPickFiles() {
			return pickFiles;
		}

		/**
		 * @param pickFiles
		 *            the pickFiles to set
		 */
		public void setPickFiles(boolean pickFiles) {
			this.pickFiles = pickFiles;
		}

		/**
		 * @return the pickFolders
		 */
		public boolean isPickFolders() {
			return pickFolders;
		}

		/**
		 * @param pickFolders
		 *            the pickFolders to set
		 */
		public void setPickFolders(boolean pickFolders) {
			this.pickFolders = pickFolders;
		}

	}

}