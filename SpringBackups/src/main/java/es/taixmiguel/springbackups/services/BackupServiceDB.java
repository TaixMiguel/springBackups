package es.taixmiguel.springbackups.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.repositories.BackupRepository;

/**
 * @author TaixMiguel
 */
@Primary
@Service("backupServiceDB")
public class BackupServiceDB implements BackupService {

	@Autowired
	private BackupRepository repository;

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
}
