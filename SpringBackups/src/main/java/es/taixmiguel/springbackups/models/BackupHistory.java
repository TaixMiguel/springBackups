package es.taixmiguel.springbackups.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Backup backup;

	private String backupName;

	private long backupSize;

	private States status;

	private float duration;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditTime;

	protected BackupHistory() {
		this.id = Long.valueOf(0);
		this.status = States.PDTE;
		this.backupSize = -1;
		this.duration = -1;
	}

	public BackupHistory(Backup backup) {
		this();
		this.backup = backup;
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

	public String getName() {
		return hasGenerated() ? backupName : "-";
	}

	public String getBackupName() {
		return backupName;
	}

	public void setBackupName(String backupName) {
		this.backupName = backupName;
	}

	public String getSize() {
		return !hasGenerated() ? "-" : "" + backupSize;
	}

	public long getBackupSize() {
		return backupSize;
	}

	public void setBackupSize(long backupSize) {
		this.backupSize = backupSize;
	}

	public States getStatus() {
		return status;
	}

	public String getStatusDescription() {
		return status.getName();
	}

	public void setStatus(States status) {
		this.status = status;
	}

	public String getFormatDuration() {
		return !hasGenerated() ? "-"
				: duration == 0 ? "0:00:00"
						: String.format("%d:%02d:%02d", duration / 3600, (duration % 3600) / 60, (duration % 60));
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getFormatTime() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(auditTime);
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	private boolean hasGenerated() {
		return backupSize > 0;
	}

	public enum States {
		PDTE("Pendiente"), CREATED("Creado"), UPLOAD("Almacenado"), ERROR("Error");

		private final String name;

		private States(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}
