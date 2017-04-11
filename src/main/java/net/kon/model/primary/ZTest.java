package net.kon.model.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "z_test")
public class ZTest {
	@Id
	@Column(name="uuid")
	private String uuid;
	
	@Column(name="vchr_code")
	private String vchrCode;
	
	@Column(name="status")
	private String status;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getVchrCode() {
		return vchrCode;
	}

	public void setVchrCode(String vchrCode) {
		this.vchrCode = vchrCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ZTest [uuid=" + uuid + ", vchrCode=" + vchrCode + ", status=" + status + "]";
	}

	
	
	
}
