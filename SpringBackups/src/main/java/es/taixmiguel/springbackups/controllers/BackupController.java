package es.taixmiguel.springbackups.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.services.BackupService;

/**
 * @author TaixMiguel
 */
@Controller
public class BackupController extends AbstractController {

	@Autowired
	private BackupService service;

	@GetMapping("/")
	public String backupList(Model model) {
		model.addAttribute("title", "Listado de backups");
		model.addAttribute("backups", service.findAll());
		return "index";
	}

	@GetMapping("/createNewBackup")
	public String createNewBackup(Model model) {
		updateModelNewBackup(model, new Backup());
		return "forms/newBackup";
	}

	@PostMapping("/backup/new/submit")
	public String newBackupSubmit(Model model, @Validated @ModelAttribute("backupForm") Backup newBackup,
			BindingResult bindingResult) {
		if (newBackup.getName().isEmpty())
			bindingResult.addError(new FieldError("name", "name", "No puede estar vacio"));
		if (newBackup.getName().contains(" "))
			bindingResult.addError(new FieldError("name", "name", "No puede incluir espacios vacios en blanco"));

		if (newBackup.getSourceDir().isEmpty())
			bindingResult.addError(new FieldError("sourceDir", "sourceDir", "No puede estar vacio"));
		if (newBackup.getDestinationDir().isEmpty())
			bindingResult.addError(new FieldError("destinationDir", "destinationDir", "No puede estar vacio"));

		if (newBackup.getStorageService() == null)
			bindingResult.addError(new FieldError("storageService", "storageService", "No puede estar vacio"));

		if (bindingResult.hasErrors()) {
			updateModelNewBackup(model, newBackup);
			return "forms/newBackup";
		}

		service.add(newBackup);
		return "redirect:/";
	}

	private void updateModelNewBackup(Model model, Backup backup) {
		model.addAttribute("title", "Creación de backup");
		model.addAttribute("backupForm", backup);
		model.addAttribute("storageServices", service.getAllStorageService());
	}
}
