package es.taixmiguel.springbackups.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taixmiguel.springbackups.models.BackupHistory;

/**
 * @author TaixMiguel
 */
public interface BackupHistoryRepository extends JpaRepository<BackupHistory, Long> {
}
