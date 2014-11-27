package org.kanomchan.core.common.dao;

import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;

public interface ActionDao {

	public List<String> getAuthorizeCodeByAction(String namespace, String actionName);

	public ActionBean findAction(String namespace, String actionName);

}
