package es.taixmiguel.springbackups.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taixmiguel.springbackups.models.Backup;
import es.taixmiguel.springbackups.models.BackupHistory;

/**
 * @author TaixMiguel
 */
public interface BackupHistoryRepository extends JpaRepository<BackupHistory, Long> {

	List<BackupHistory> findByBackup(Backup backup);
}
