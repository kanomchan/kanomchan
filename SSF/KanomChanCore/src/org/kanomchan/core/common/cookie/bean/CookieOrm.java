package org.kanomchan.core.common.cookie.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="SYS_T_COOKIE")
public class CookieOrm  implements Serializable{

	@Id
	@Column(name="MACHINE_ID")
	private String machineId;
	@Column(name="USER_ID")
	private String userId;
	@Column(name="KEY_ID")
	private String keyId;
	@Column(name="TOKEN_ID")
	private String tokenId;
	@Column(name="EXPIRE")
	private Date expire;
	@Column(name="ACTIVE")
	private Date active;
	
	
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKeyId() {
		return keyId;
	}
	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public Date getActive() {
		return active;
	}
	public void setActive(Date active) {
		this.active = active;
	}
	
	
}
