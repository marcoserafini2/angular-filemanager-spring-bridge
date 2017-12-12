package it.ms.filemanager.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.ms.filemanager.api.handler.LogInterceptor;
import it.ms.filemanager.api.lang.FileManagerException;
import it.ms.filemanager.configuration.ConfigFileManager;
import it.ms.filemanager.model.response.Item;
import it.ms.filemanager.model.response.Item.Type;
import it.ms.filemanager.service.FileManager;
import it.ms.filemanager.utils.PermissionUtils;

@Service
public class FileManagerImpl implements FileManager {

	private final static Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

	@Autowired
	ConfigFileManager configFileManager;

	/**
	 * {@inheritDoc}}
	 *
	 * @return
	 */
	@Override
	public List<Item> list(String path) {

		LOG.debug("List path: Paths.get('{}', '{}'), onlyFolders: {}", configFileManager.getRepoBasePath(), path,
				configFileManager.isOnlyFolders());

		List<Item> items = new ArrayList<>();

		try (DirectoryStream<Path> directoryStream = Files
				.newDirectoryStream(Paths.get(configFileManager.getRepoBasePath(), path))) {
			for (Path p : directoryStream) {
				BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
				if (configFileManager.isOnlyFolders() && !attrs.isDirectory()) {
					continue;
				}
				Item item = new Item();
				item.setName(p.getFileName().toString());
				item.setRights(PermissionUtils.getPermissions(p));
				item.setDate(new Date(attrs.lastModifiedTime().toMillis()));
				item.setSize(attrs.size());
				item.setType(attrs.isDirectory() ? Type.dir : Type.file);
				items.add(item);
			}

		} catch (IOException e) {
			throw new FileManagerException("Expection in list action", e);
		}

		return items;
	}

	@Override
	public boolean rename(String itemPath, String newItemPath) {

		try {
			LOG.debug("rename from: {} to: {}", itemPath, newItemPath);
			File srcFile = new File(configFileManager.getRepoBasePath(), itemPath);
			File destFile = new File(configFileManager.getRepoBasePath(), newItemPath);
			if (srcFile.isFile()) {
				FileUtils.moveFile(srcFile, destFile);
			} else {
				FileUtils.moveDirectory(srcFile, destFile);
			}
			return true;
		} catch (IOException e) {
			throw new FileManagerException("Expection in rename action", e);
		}
	}

	@Override
	public boolean move(List<String> items, String newItemPath) {
		try {
			Path newpath = Paths.get(configFileManager.getRepoBasePath(), newItemPath);
			for (String item : items) {
				Path path = Paths.get(configFileManager.getRepoBasePath(), item);
				Path mpath = newpath.resolve(path.getFileName());
				LOG.debug("mv {} to {} exists? {}", path, mpath, Files.exists(mpath));
				if (Files.exists(mpath)) {
					throw new FileManagerException("File already exist");
				}
				Files.move(path, mpath, StandardCopyOption.REPLACE_EXISTING);
			}
			return true;
		} catch (IOException e) {
			throw new FileManagerException("Error in move ation", e);
		}
	}

	@Override
	public boolean copy(List<String> items, String newPath, String newFileName) {
		try {
			Path newpath = Paths.get(configFileManager.getRepoBasePath(), newPath);
			for (String item : items) {
				Path path = newFileName == null ? Paths.get(configFileManager.getRepoBasePath(), item)
						: Paths.get(".", newFileName);
				Path mpath = newpath.resolve(path.getFileName());
				LOG.debug("mv {} to {} exists? {}", path, mpath, Files.exists(mpath));
				if (Files.exists(mpath)) {
					throw new FileManagerException("File already exist");
				}
				path = Paths.get(configFileManager.getRepoBasePath(), item);
				mpath = newpath
						.resolve(newFileName == null ? path.getFileName() : Paths.get(".", newFileName).getFileName());
				Files.copy(path, mpath, StandardCopyOption.REPLACE_EXISTING);
			}
			return true;
		} catch (IOException e) {
			throw new FileManagerException("Error in copy ation", e);
		}
	}

	@Override
	public int remove(List<String> items) {
		int countRemove = 0;
		for (String item : items) {
			Path path = Paths.get(configFileManager.getRepoBasePath(), item);
			if (FileUtils.deleteQuietly(path.toFile())) {
				LOG.debug("remove {}", path);
				countRemove++;
			}
		}
		if (countRemove == 0) {
			throw new FileManagerException("No item remove");
		} else {
			return countRemove;
		}
	}

	@Override
	public boolean edit(String path, String content) {
		try {
			LOG.debug("editFile path: {}", path);
			File srcFile = new File(configFileManager.getRepoBasePath(), path);
			FileUtils.writeStringToFile(srcFile, content);
			return true;
		} catch (IOException ex) {
			throw new FileManagerException("Error in edit action", ex);
		}
	}

	@Override
	public String getContent(String item) {
		try {
			return FileUtils.readFileToString(Paths.get(configFileManager.getRepoBasePath(), item).toFile());
		} catch (IOException ex) {
			throw new FileManagerException("Error in get content action", ex);
		}
	}

	@Override
	public boolean changePermission(List<String> items, String perms, String permsCode, boolean recursive) {
		try {
			for (String item : items) {
				LOG.debug("changepermissions path: {} perms: {} permsCode: {} recursive: {}", item, perms, permsCode,
						recursive);
				File f = Paths.get(configFileManager.getRepoBasePath(), item).toFile();
				PermissionUtils.setPermissions(f, perms, recursive);
			}
			return true;
		} catch (IOException ex) {
			throw new FileManagerException("Error in changePermission action", ex);
		}
	}

	@Override
	public boolean createFolder(String newPath) {
		try {
			Path path = Paths.get(configFileManager.getRepoBasePath(), newPath);
			LOG.debug("createFolder path: {} name: {}", path);
			Files.createDirectories(path);
			return true;
		} catch (FileAlreadyExistsException ex) {
			throw new FileManagerException("Error in create-folder action, folder already exists", ex);
		} catch (IOException e) {
			throw new FileManagerException("Error in create-folder action", e);
		}
	}

	@Override
	public boolean compress(List<String> items, String destination, String compressedFilename) {
		try {
			final Path dest = Paths.get(configFileManager.getRepoBasePath(), destination);
			Path zip = dest.resolve(compressedFilename);
			if (Files.exists(zip)) {
				throw new FileManagerException("Error in compress action, zip already exists");
			}
			Map<String, String> env = new HashMap<>();
			env.put("create", "true");
			boolean zipped = false;
			try (FileSystem zipfs = FileSystems.newFileSystem(URI.create("jar:" + zip.toUri().toString()), env)) {
				for (String item : items) {
					Path realPath = Paths.get(configFileManager.getRepoBasePath(), item);
					if (Files.isDirectory(realPath)) {
						Files.walkFileTree(Paths.get(configFileManager.getRepoBasePath(), item),
								new SimpleFileVisitor<Path>() {
									@Override
									public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
											throws IOException {
										Files.createDirectories(
												zipfs.getPath(dir.toString().substring(dest.toString().length())));
										return FileVisitResult.CONTINUE;
									}

									@Override
									public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
											throws IOException {
										Path pathInZipFile = zipfs
												.getPath(file.toString().substring(dest.toString().length()));
										LOG.debug("compress: '{}'", pathInZipFile);
										Files.copy(file, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
										return FileVisitResult.CONTINUE;
									}
								});
					} else {
						Path pathInZipFile = zipfs.getPath("/", realPath.toString()
								.substring(configFileManager.getRepoBasePath().length() + destination.length()));
						Path pathInZipFolder = pathInZipFile.getParent();
						if (!Files.isDirectory(pathInZipFolder)) {
							Files.createDirectories(pathInZipFolder);
						}
						LOG.debug("compress: '{}]", pathInZipFile);
						Files.copy(realPath, pathInZipFile, StandardCopyOption.REPLACE_EXISTING);
					}
				}
				zipped = true;
			} finally {
				if (!zipped) {
					Files.deleteIfExists(zip);
				}
			}
			return true;
		} catch (IOException ex) {
			throw new FileManagerException("Error in compress action, folder already exists", ex);
		}
	}

	@Override
	public boolean extract(String zip, String destination, String folderName) {
		boolean genFolder = false;
		Path dest = Paths.get(configFileManager.getRepoBasePath(), destination);
		final Path folder = dest.resolve(folderName);
		try {
			if (!Files.isDirectory(folder)) {
				genFolder = true;
				Files.createDirectories(folder);
			}
			Map<String, String> env = new HashMap<>();
			env.put("create", "false");
			try (FileSystem zipfs = FileSystems.newFileSystem(
					URI.create("jar:" + Paths.get(configFileManager.getRepoBasePath(), zip).toUri()), env)) {
				Files.walkFileTree(zipfs.getPath("/"), new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						if (file.getNameCount() > 0) {
							Path dest = folder.resolve(
									file.getNameCount() < 1 ? "" : file.subpath(0, file.getNameCount()).toString());
							LOG.debug("extract {} to {}", file, dest);
							try {
								Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
							} catch (Exception ex) {
								LOG.error(ex.getMessage(), ex);
							}
						}
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
						Path subFolder = folder
								.resolve(dir.getNameCount() < 1 ? "" : dir.subpath(0, dir.getNameCount()).toString());
						if (!Files.exists(subFolder)) {
							Files.createDirectories(subFolder);
						}
						return FileVisitResult.CONTINUE;
					}

				});
			}
			return true;
		} catch (IOException ex) {
			if (genFolder) {
				FileUtils.deleteQuietly(folder.toFile());
			}
			throw new FileManagerException("Error in compress action, folder already exists", ex);
		}
	}

	@Override
	public boolean store(MultipartFile multipartFile, String newItemPath) {
		String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            if (multipartFile.isEmpty()) {
                throw new FileManagerException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new FileManagerException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
			Path newpath = Paths.get(configFileManager.getRepoBasePath(), newItemPath);
            Files.copy(multipartFile.getInputStream(), newpath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
    		return true;
        } catch (IOException e) {
            throw new FileManagerException("Failed to store file " + filename, e);
        }
	}

	@Override
	public File getFile(String pathFile) {
		Path path = Paths.get(configFileManager.getRepoBasePath(), pathFile);
		return path.toFile();
	}

}
