package org.kanomchan.core.common.bean;

import java.util.Date;

public interface EntityBean {

	public String getRowStatus();

	public void setRowStatus(String rowStatus);

	public Date getTimeCreate();

	public void setTimeCreate(Date timeCreate);

	public Date getTimeUpdate();

	public void setTimeUpdate(Date timeUpdate);

	public String getUserCreate();

	public void setUserCreate(String userCreate);

	public String getUserUpdate();

	public void setUserUpdate(String userUpdate);
	

}
