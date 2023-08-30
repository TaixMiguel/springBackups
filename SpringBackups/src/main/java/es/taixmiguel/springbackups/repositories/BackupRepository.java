package es.taixmiguel.springbackups.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taixmiguel.springbackups.models.Backup;

/**
 * @author TaixMiguel
 */
public interface BackupRepository extends JpaRepository<Backup, Long> {
}
