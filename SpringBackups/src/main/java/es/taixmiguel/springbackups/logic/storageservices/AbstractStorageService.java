package es.taixmiguel.springbackups.logic.storageservices;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.BackupHistory;

/**
 * @author TaixMiguel
 */
public abstract class AbstractStorageService implements IStorageService {

	private final BackupHistory backupHistory;
	private final String destinationDir;

	protected AbstractStorageService(BackupHistory backupHistory) {
		this.backupHistory = backupHistory;
		Backup backup = backupHistory.getBackup();
		this.destinationDir = backup.getDestinationDir();
	}

	public static IStorageService getInstance(BackupHistory backupHistory) {
		Backup backup = backupHistory.getBackup();

		switch (backup.getStorageService()) {
		case LOCAL:
			return new LocalStorageService(backupHistory);
		default:
			throw new IllegalArgumentException("Unexpected value: " + backup.getStorageService());
		}
	}

	protected BackupHistory getBackupHistory() {
		return backupHistory;
	}

	protected String getDestinationDir() {
		return destinationDir;
	}

	protected String getNameFileBackup() {
		return backupHistory.getBackupName();
	}
}
