package es.taixmiguel.springbackups.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.BackupHistory;
import es.taixmiguel.springbackups.repositories.BackupHistoryRepository;

/**
 * @author TaixMiguel
 */
@Service
public class BackupHistoryService {

	@Autowired
	private BackupHistoryRepository repository;

	public List<BackupHistory> history(Backup backup) {
		return repository.findByBackup(backup);
	}

	public List<BackupHistory> historyByState(Backup backup, BackupHistory.States state) {
		return repository.findByBackupAndStatus(backup, state);
	}

	public BackupHistory save(BackupHistory history) {
		return repository.save(history);
	}

	public BackupHistory saveNewState(BackupHistory history, BackupHistory.States state) {
		history.setStatus(state);
		return repository.save(history);
	}

	public void delete(BackupHistory history) {
		repository.delete(history);
	}
}
