package es.taixmiguel.springbackups.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.StorageService;
import es.taixmiguel.springbackups.repositories.BackupRepository;
import es.taixmiguel.springbackups.repositories.StorageServiceRepository;

/**
 * @author TaixMiguel
 */
@Primary
@Service("backupServiceDB")
public class BackupServiceDB implements BackupService {

	@Autowired
	private BackupRepository repository;
	@Autowired
	private StorageServiceRepository storageRepository;

	@Override
	public Backup add(Backup backup) {
		return repository.save(backup);
	}

	@Override
	public List<Backup> findAll() {
		return repository.findAll();
	}

	@Override
	public List<StorageService> getAllStorageService() {
		return storageRepository.findAll();
	}
}
