package es.taixmiguel.springbackups.logic.storageservices;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import es.taixmiguel.springbackups.models.BackupHistory;

/**
 * @author TaixMiguel
 */
public class LocalStorageService extends AbstractStorageService {

	public LocalStorageService(BackupHistory backupHistory) {
		super(backupHistory);
	}

	@Override
	public void push(File backup) throws IOException {
		Path directory = Paths.get(getDestinationDir());
		if (Files.notExists(directory))
			Files.createDirectory(directory);

		Path pathBackup = Paths.get(backup.getPath());
		Files.move(pathBackup, getPathBackupFile());
	}

	@Override
	public Optional<File> pull() {
		Path backupFile = getPathBackupFile();
		if (Files.exists(backupFile))
			return Optional.ofNullable(backupFile.toFile());

		return Optional.empty();
	}

	@Override
	public Optional<File> delete() throws IOException {
		Optional<File> backup = pull();
		if (backup.isPresent())
			Files.delete(getPathBackupFile());

		return backup;
	}

	private Path getPathBackupFile() {
		return Paths.get(getDestinationDir(), getNameFileBackup());
	}
}
