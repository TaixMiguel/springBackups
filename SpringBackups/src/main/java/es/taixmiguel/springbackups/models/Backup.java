package es.taixmiguel.springbackups.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

/**
 * @author TaixMiguel
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Backup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	private StorageService storageService;

	@Column(nullable = false)
	private String sourceDir;

	@Column(nullable = false)
	private String destinationDir;

	private String username;

	private String password;

	private int nBackupsMax;

	private boolean swSensorMQTT;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;

	@Transient
	private List<BackupHistory> history;

	public Backup() {
		this.id = Long.valueOf(0);
		this.nBackupsMax = 10;
		this.swSensorMQTT = false;
		this.history = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StorageService getStorageService() {
		return storageService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}

	public String getDestinationDir() {
		return destinationDir;
	}

	public void setDestinationDir(String destinationDir) {
		this.destinationDir = destinationDir;
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getnBackupsMax() {
		return nBackupsMax;
	}

	public void setnBackupsMax(int nBackupsMax) {
		this.nBackupsMax = nBackupsMax;
	}

	public boolean isSwSensorMQTT() {
		return swSensorMQTT;
	}

	public void setSwSensorMQTT(boolean swSensorMQTT) {
		this.swSensorMQTT = swSensorMQTT;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public List<BackupHistory> getHistory() {
		return history;
	}

	public void setHistory(List<BackupHistory> history) {
		this.history = history;
	}

	@Override
	public String toString() {
		return "Backup [id=" + id + ", name=" + name + ", description=" + description + ", storageService="
				+ storageService + ", sourceDir=" + sourceDir + ", destinationDir=" + destinationDir + ", user="
				+ username + ", password=" + password + ", nBackupsMax=" + nBackupsMax + ", swSensorMQTT="
				+ swSensorMQTT + ", auditTime=" + auditTime + "]";
	}
}
