package es.taixmiguel.springbackups.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * @author TaixMiguel
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class BackupHistory {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Backup backup;

	private int backupSize;

	private String status;

	private float duration;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;

	protected BackupHistory() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Backup getBackup() {
		return backup;
	}

	public void setBackup(Backup backup) {
		this.backup = backup;
	}

	public int getBackupSize() {
		return backupSize;
	}

	public void setBackupSize(int backupSize) {
		this.backupSize = backupSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
}
