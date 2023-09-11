package es.taixmiguel.springbackups.logic.backup;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import es.taixmiguel.springbackups.models.Backup;

/**
 * @author TaixMiguel
 */
@Component
public class AsyncBackupCreator {

	public AsyncBackupCreator() {
	}

	@Async
	public void createBackup(Backup backupModel) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		System.out.println("Termine");
	}
}
