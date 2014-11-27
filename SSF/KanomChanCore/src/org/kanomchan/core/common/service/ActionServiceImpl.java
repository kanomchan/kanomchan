package org.kanomchan.core.common.service;

import java.util.HashSet;
import java.util.Set;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.dao.ActionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class ActionServiceImpl implements ActionService {

	private ActionDao actionDao;
	@Autowired
	@Required
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	
	@Override
	public Set<String> getAuthorizeCodeByAction(String namespace, String actionName) {
		return new HashSet<String>(actionDao.getAuthorizeCodeByAction(namespace,actionName));
	}

	@Override
	public ActionBean findAction(String namespace, String actionName) {
		return actionDao.findAction(namespace,actionName);
	}

}
