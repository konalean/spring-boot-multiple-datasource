package net.kon.model.secondary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "z_user")
public class ZUser {
	@Id
	@Column(name="adm_id")
	private int admId;
	
	@Column(name="account")
	private String account;

	public int getAdmId() {
		return admId;
	}

	public void setAdmId(int admId) {
		this.admId = admId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "ZUser [admId=" + admId + ", account=" + account + "]";
	}
	
	
}
