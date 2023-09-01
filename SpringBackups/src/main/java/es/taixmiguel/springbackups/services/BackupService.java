package es.taixmiguel.springbackups.services;

import java.util.List;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.StorageService;

/**
 * @author TaixMiguel
 */
public interface BackupService {

	public Backup add(Backup backup);

	public List<Backup> findAll();

	public List<StorageService> getAllStorageService();
}
