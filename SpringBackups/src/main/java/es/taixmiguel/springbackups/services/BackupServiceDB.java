package es.taixmiguel.springbackups.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.repositories.BackupHistoryRepository;
import es.taixmiguel.springbackups.repositories.BackupRepository;

/**
 * @author TaixMiguel
 */
@Primary
@Service("backupServiceDB")
public class BackupServiceDB implements BackupService {

	@Autowired
	private BackupRepository repository;

	@Autowired
	private BackupHistoryRepository historyRepo;

	@Override
	public Backup add(Backup backup) {
		return repository.save(backup);
	}

	@Override
	public Backup update(Backup backup) {
		return repository.save(backup);
	}

	@Override
	public Backup remove(Long id) {
		Backup backup = findById(id);
		repository.delete(backup);
		return backup;
	}

	@Override
	public Backup findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Backup> findAll() {
		return repository.findAll();
	}

	@Override
	public Backup findCompleteById(Long id) {
		Optional<Backup> backup = repository.findById(id);
		if (backup.isPresent())
			completeBackup(backup.get());
		return backup.orElse(null);
	}

	@Override
	public List<Backup> findComplete() {
		List<Backup> backups = findAll();
		for (Backup backup : backups)
			completeBackup(backup);
		return backups;
	}

	private void completeBackup(Backup backup) {
		backup.setHistory(historyRepo.findByBackup(backup));
	}
}
