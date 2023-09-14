package es.taixmiguel.springbackups.logic.backup;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import es.taixmiguel.springbackups.logic.storageservices.AbstractStorageService;
import es.taixmiguel.springbackups.logic.storageservices.IStorageService;
import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.BackupHistory;
import es.taixmiguel.springbackups.services.BackupHistoryService;

/**
 * @author TaixMiguel
 */
@Component
public class AsyncBackupCreator {

	@Autowired
	private BackupHistoryService service;

	public AsyncBackupCreator() {
	}

	@Async
	public void createNewBackup(Backup backupModel) {
		BackupHistory history = new BackupHistory(backupModel);
		history = service.save(history);

		try {
			ToolBackupCreator creator = createBackup(backupModel, history);
			pushBackup(history, creator.getBackup());
		} catch (IOException e) {
			service.saveNewState(history, BackupHistory.States.ERROR);
			e.printStackTrace();
		}

		try {
			deleteOldHistory(backupModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ToolBackupCreator createBackup(Backup backup, BackupHistory history) throws IOException {
		ToolBackupCreator creator = new ToolBackupCreator(backup);
		creator.generateBackup();
		service.save(creator.updateHistory(history));
		return creator;
	}

	private void pushBackup(BackupHistory history, File backup) throws IOException {
		IStorageService storage = AbstractStorageService.getInstance(history);
		storage.push(backup);
		backup.delete();
		service.saveNewState(history, BackupHistory.States.UPLOAD);
	}

	private void deleteOldHistory(Backup backup) throws IOException {
		if (service.historyByState(backup, BackupHistory.States.UPLOAD).size() > backup.getnBackupsMax()) {
			List<BackupHistory> histories = service.history(backup);
			for (BackupHistory history : histories) {
				AbstractStorageService.getInstance(history).delete();
				service.delete(history);
				if (history.getStatus() == BackupHistory.States.UPLOAD)
					break;
			}
		}
	}
}
