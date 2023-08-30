package es.taixmiguel.springbackups.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * @author TaixMiguel
 */
@Entity
public class StorageService {

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 8, unique = true)
	private String code;

	private String name;

	protected StorageService() {
	}

	public StorageService(String code, String name) {
		setCode(code);
		setName(name);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
