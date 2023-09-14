package es.taixmiguel.springbackups.logic.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.BackupHistory;

/**
 * @author TaixMiguel
 */
final class ToolBackupCreator {

	private final Path backup;
	private final Path source;
	private Duration duration;

	public ToolBackupCreator(Backup backup) {
		this.backup = Paths.get(getNameFile(backup));
		this.source = Paths.get(backup.getSourceDir());
	}

	public void generateBackup() throws IOException {
		Instant start = Instant.now();
		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(backup.toString()))) {
			Files.walkFileTree(source, new SimpleFileVisitor<>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {

					if (attributes.isSymbolicLink())
						return FileVisitResult.CONTINUE;

					try (FileInputStream fis = new FileInputStream(file.toFile())) {
						Path targetFile = source.relativize(file);
						zos.putNextEntry(new ZipEntry(targetFile.toString()));

						byte[] buffer = new byte[1024];
						int len;
						while ((len = fis.read(buffer)) > 0)
							zos.write(buffer, 0, len);

						zos.closeEntry();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					System.err.printf("Unable to zip : %s%n%s%n", file, exc);
					return FileVisitResult.CONTINUE;
				}
			});
		}
		Instant end = Instant.now();
		this.duration = Duration.between(start, end);
	}

	public BackupHistory updateHistory(BackupHistory history) throws IOException {
		if (Files.exists(this.backup)) {
			history.setBackupSize(Files.size(backup));
			history.setBackupName(this.backup.toString());
			history.setDuration(this.duration.getSeconds());
			history.setStatus(BackupHistory.States.CREATED);
		}
		return history;
	}

	public File getBackup() {
		return backup != null ? backup.toFile() : null;
	}

	private String getNameFile(Backup backup) {
		return String.format("%s_%s.%s", backup.getName(),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), "zip");
	}
}
