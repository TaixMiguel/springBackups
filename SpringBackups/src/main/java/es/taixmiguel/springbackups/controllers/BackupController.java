package es.taixmiguel.springbackups.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.taixmiguel.springbackups.logic.backup.AsyncBackupCreator;
import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.StorageService;
import es.taixmiguel.springbackups.services.BackupService;

/**
 * @author TaixMiguel
 */
@Controller
public class BackupController extends AbstractController {

	@Autowired
	private BackupService service;

	@Autowired
	private AsyncBackupCreator backupCreator;

	@GetMapping("/")
	public String backupList(Model model) {
		model.addAttribute("title", "Listado de backups");
		model.addAttribute("backups", service.findComplete());
		return "index";
	}

	@GetMapping("/createNewBackup")
	public String createNewBackup(Model model) {
		updateModelNewBackup(model, new Backup());
		return "forms/newBackup";
	}

	@GetMapping("/updateBackup/{id}")
	public String updateBackup(Model model, @PathVariable Long id) {
		updateModelUpdateBackup(model, service.findById(id));
		return "forms/newBackup";
	}

	@GetMapping("/deleteBackup/{id}")
	public String deleteBackup(Model model, @PathVariable Long id) {
		service.remove(id);
		return "redirect:/";
	}

	@GetMapping("/execBackup/{id}")
	public String executeBackup(Model model, @PathVariable Long id) {
		model.addAttribute("title", "Ejecución de backup");
		Backup backup = service.findById(id);
		model.addAttribute("backup", backup);
		backupCreator.createBackup(backup);
		return "executeBackup";
	}

	@PostMapping("/backup/new/submit")
	public String newBackupSubmit(Model model, @Validated @ModelAttribute("backupForm") Backup newBackup,
			BindingResult bindingResult) {
		validateModelBackup(newBackup, bindingResult);

		if (bindingResult.hasErrors()) {
			updateModelNewBackup(model, newBackup);
			return "forms/newBackup";
		}

		service.add(newBackup);
		return "redirect:/";
	}

	@PostMapping("/backup/edit/submit")
	public String editBackupSubmit(Model model, @Validated @ModelAttribute("backupForm") Backup editBackup,
			BindingResult bindingResult) {
		validateModelBackup(editBackup, bindingResult);

		if (bindingResult.hasErrors()) {
			updateModelNewBackup(model, editBackup);
			return "forms/newBackup";
		}

		service.update(editBackup);
		return "redirect:/";
	}

	private void validateModelBackup(Backup backup, BindingResult bindingResult) {
		if (backup.getName().isEmpty())
			bindingResult.addError(new FieldError("name", "name", "No puede estar vacio"));
		if (backup.getName().contains(" "))
			bindingResult.addError(new FieldError("name", "name", "No puede incluir espacios vacios en blanco"));

		if (backup.getSourceDir().isEmpty())
			bindingResult.addError(new FieldError("sourceDir", "sourceDir", "No puede estar vacio"));
		if (backup.getDestinationDir().isEmpty())
			bindingResult.addError(new FieldError("destinationDir", "destinationDir", "No puede estar vacio"));

		if (backup.getStorageService() == null)
			bindingResult.addError(new FieldError("storageService", "storageService", "No puede estar vacio"));
	}

	private void updateModelNewBackup(Model model, Backup backup) {
		updateModelBackup(model, backup, "Creación de backup");
	}

	private void updateModelUpdateBackup(Model model, Backup backup) {
		updateModelBackup(model, backup, "Actualización de backup");
	}

	private void updateModelBackup(Model model, Backup backup, String title) {
		model.addAttribute("title", title);
		model.addAttribute("backupForm", backup);
		model.addAttribute("storageServices", StorageService.values());
	}
}
