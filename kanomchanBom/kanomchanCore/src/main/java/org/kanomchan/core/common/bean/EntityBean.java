package org.kanomchan.core.common.bean;

import java.util.Date;

public interface EntityBean {

	public String getStatus();

	public void setStatus(String rowStatus);

	public Date getCreateDate();

	public void setCreateDate(Date timeCreate);

	public Date getUpdateDate();

	public void setUpdateDate(Date timeUpdate);

	public String getCreateUser();

	public void setCreateUser(String userCreate);

	public String getUpdateUser();

	public void setUpdateUser(String userUpdate);
	

}
