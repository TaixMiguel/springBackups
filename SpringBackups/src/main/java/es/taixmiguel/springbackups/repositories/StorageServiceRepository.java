package es.taixmiguel.springbackups.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.taixmiguel.springbackups.models.StorageService;

/**
 * @author TaixMiguel
 */
public interface StorageServiceRepository extends JpaRepository<StorageService, Long> {
}
