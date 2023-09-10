package es.taixmiguel.springbackups.services;

import java.util.List;

import es.taixmiguel.springbackups.models.Backup;

/**
 * @author TaixMiguel
 */
public interface BackupService {

	public Backup add(Backup backup);

	public Backup update(Backup backup);

	public Backup remove(Long id);

	public Backup findById(Long id);

	public List<Backup> findAll();
}
