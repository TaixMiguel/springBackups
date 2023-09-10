package es.taixmiguel.springbackups.models;

/**
 * @author TaixMiguel
 */
public enum StorageService {
	LOCAL("Servidor local");

	private final String name;

	private StorageService(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
