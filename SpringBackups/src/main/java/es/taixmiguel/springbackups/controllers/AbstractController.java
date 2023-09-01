package es.taixmiguel.springbackups.controllers;

import java.io.IOException;
import java.util.Properties;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author TaixMiguel
 */
public abstract class AbstractController {

	@ModelAttribute("appName")
	public String appName() {
		return "Generador de backups";
	}

	@ModelAttribute("appVersion")
	public String appVersion() {
		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
			return prop.getProperty("info.app.version");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "beta";
	}
}
