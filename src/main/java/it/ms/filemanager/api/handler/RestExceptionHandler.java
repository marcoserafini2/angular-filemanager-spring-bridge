package it.ms.filemanager.api.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.ms.filemanager.api.lang.FileManagerException;
import it.ms.filemanager.model.response.ErrorResponse;

@ControllerAdvice
@RestController
public class RestExceptionHandler {

	private final static Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = FileManagerException.class)
	public @ResponseBody ErrorResponse handleFileManagerException(FileManagerException fileManagerException) {
		LOG.error("Error {}", fileManagerException.getMessage(), fileManagerException.getCause());
		return new ErrorResponse(fileManagerException.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Throwable.class)
	public @ResponseBody String handleThrowable(Throwable throwable) {
		LOG.error("Error ", throwable);
		return throwable.getMessage();
	}

}