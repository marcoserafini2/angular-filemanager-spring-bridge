package it.ms.filemanager.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.ms.filemanager.model.request.ChangePermission;
import it.ms.filemanager.model.request.Compress;
import it.ms.filemanager.model.request.Copy;
import it.ms.filemanager.model.request.CreateFolder;
import it.ms.filemanager.model.request.Edit;
import it.ms.filemanager.model.request.Extract;
import it.ms.filemanager.model.request.GetContent;
import it.ms.filemanager.model.request.GetList;
import it.ms.filemanager.model.request.Move;
import it.ms.filemanager.model.request.Remove;
import it.ms.filemanager.model.request.Rename;
import it.ms.filemanager.model.response.Item;
import it.ms.filemanager.model.response.ResponseModel;
import it.ms.filemanager.model.response.ResponseStatus;
import it.ms.filemanager.model.response.SuccessfulResponse;
import it.ms.filemanager.service.FileManager;

/**
 * @see https://github.com/joni2back/angular-filemanager/blob/master/API.md
 * @author Marco Serafini
 *
 */
@RestController
@RequestMapping(path = "action", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class FileManagerRest {

	@Autowired
	FileManager fileManager;

	/**
	 * Listing (URL: fileManagerConfig.listUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/list", method = RequestMethod.POST, name = "list")
	public @ResponseBody ResponseModel<List<Item>> list(@RequestBody GetList params) {
		return new ResponseModel<>(fileManager.list(params.getPath()));
	}

	/**
	 * Rename (URL: fileManagerConfig.renameUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/rename", method = RequestMethod.POST, name = "rename")
	public @ResponseBody ResponseModel<ResponseStatus> rename(@RequestBody Rename params) {
		fileManager.rename(params.getItem(), params.getNewItemPath());
		return new SuccessfulResponse();
	}

	/**
	 * Move (URL: fileManagerConfig.moveUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/move", method = RequestMethod.POST, name = "move")
	public @ResponseBody ResponseModel<ResponseStatus> move(@RequestBody Move params) {
		fileManager.move(params.getItems(), params.getNewPath());
		return new SuccessfulResponse();
	}

	/**
	 * Copy (URL: fileManagerConfig.copyUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/copy", method = RequestMethod.POST, name = "copy")
	public @ResponseBody ResponseModel<ResponseStatus> copy(@RequestBody Copy params) {
		fileManager.copy(params.getItems(), params.getNewPath(), params.getSingleFilename());
		return new SuccessfulResponse();
	}

	/**
	 * Remove (URL: fileManagerConfig.removeUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/remove", method = RequestMethod.POST, name = "remove")
	public @ResponseBody ResponseModel<ResponseStatus> remove(@RequestBody Remove params) {
		fileManager.remove(params.getItems());
		return new SuccessfulResponse();
	}

	/**
	 * Edit file (URL: fileManagerConfig.editUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/edit", method = RequestMethod.POST, name = "edit")
	public @ResponseBody ResponseModel<ResponseStatus> edit(@RequestBody Edit params) {
		return new ResponseModel<>(new ResponseStatus(fileManager.edit(params.getItem(), params.getContent())));
	}

	/**
	 * Get content of a file (URL: fileManagerConfig.getContentUrl, Method:
	 * POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/get-content", method = RequestMethod.POST, name = "getContent")
	public @ResponseBody ResponseModel<String> getContent(@RequestBody GetContent params) {
		return new ResponseModel<>(fileManager.getContent(params.getItem()));
	}

	/**
	 * Create folder (URL: fileManagerConfig.createFolderUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/create-folder", method = RequestMethod.POST, name = "createFolder")
	public @ResponseBody ResponseModel<ResponseStatus> createFolder(@RequestBody CreateFolder params) {
		fileManager.createFolder(params.getNewPath());
		return new SuccessfulResponse();
	}

	/**
	 * Change Permission (URL: fileManagerConfig.permissionUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/permission", method = RequestMethod.POST, name = "changePermission")
	public @ResponseBody ResponseModel<ResponseStatus> changePermission(@RequestBody ChangePermission params) {
		fileManager.changePermission(params.getItems(), params.getPerms(), params.getPermsCode(), params.isRecursive());
		return new SuccessfulResponse();
	}

	/**
	 * Compress file (URL: fileManagerConfig.compressUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/compress", method = RequestMethod.POST, name = "compress")
	public @ResponseBody ResponseModel<ResponseStatus> compress(@RequestBody Compress params) {
		fileManager.compress(params.getItems(), params.getDestination(), params.getCompressedFilename());
		return new SuccessfulResponse();
	}

	/**
	 * Extract file (URL: fileManagerConfig.extractUrl, Method: POST)
	 *
	 * @return
	 */
	@RequestMapping(path = "/extract", method = RequestMethod.POST, name = "extract")
	public @ResponseBody ResponseModel<ResponseStatus> extract(@RequestBody Extract params) {
		fileManager.extract(params.getItem(), params.getFolderName(), params.getDestination());
		return new SuccessfulResponse();
	}

	/**
	 * Upload file (URL: fileManagerConfig.uploadUrl, Method: POST,
	 * Content-Type: multipart/form-data)
	 *
	 * @return
	 *
	 * @return
	 */
	@RequestMapping(path = "/upload",
			consumes= MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST, name = "upload")
	public @ResponseBody ResponseModel<ResponseStatus> upload(
			@RequestParam(name="file") List<MultipartFile> files,
			@RequestParam(name = "destination", required = true) String destination) {
		files.stream().forEach((m) -> fileManager.store(m, destination));
		return new SuccessfulResponse();
	}

	/**
	 * Download / Preview file (URL: fileManagerConfig.downloadMultipleUrl,
	 * Method: GET)
	 *
	 * @return
	 */
	@RequestMapping(path = "/download",
			method = RequestMethod.GET,
			consumes = MediaType.ALL_VALUE,
			produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, name = "download")
	public FileSystemResource download(HttpServletResponse response, @RequestParam(name = "path", required = true) String path) {
	    return new FileSystemResource(fileManager.getFile(path));
	}

	/**
	 * Download multiples files in ZIP/TAR (URL:
	 * fileManagerConfig.downloadFileUrl, Method: GET)
	 *
	 * @return
	 */
	@RequestMapping(path = "/download-multiple", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, name = "downloadMultiple")
	public void downloadMultiple(HttpServletResponse response) {
		//TODO
	}

}
