package it.ms.filemanager.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.ms.filemanager.configuration.ConfigFileManager;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfigurationRest {

	@Autowired
	ConfigFileManager configFileManagerg;

	/**
	 * Configuratione REST
	 *
	 * @return
	 */
	@RequestMapping(path = "/configuration", method = RequestMethod.GET, name = "configuration")
	public @ResponseBody ConfigFileManager configuration() {
		return this.configFileManagerg;
	}

}