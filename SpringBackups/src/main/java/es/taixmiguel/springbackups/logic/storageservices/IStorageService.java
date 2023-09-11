package es.taixmiguel.springbackups.logic.storageservices;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author TaixMiguel
 */
public interface IStorageService {
	void push(File backup) throws IOException;

	Optional<File> pull();

	Optional<File> delete() throws IOException;
}
