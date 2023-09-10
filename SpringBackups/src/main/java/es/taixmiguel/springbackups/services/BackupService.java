package es.taixmiguel.springbackups.services;

import java.util.List;

import es.taixmiguel.springbackups.models.Backup;

/**
 * @author TaixMiguel
 */
public interface BackupService {

	public Backup add(Backup backup);

	public List<Backup> findAll();
}
