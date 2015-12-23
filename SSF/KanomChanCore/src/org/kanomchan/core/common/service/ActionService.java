package org.kanomchan.core.common.service;

import java.util.Set;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface ActionService {

	public Set<String> getAuthorizeCodeByAction(String namespace, String actionName) throws RollBackException, NonRollBackException;
	
	public void refresh() throws RollBackException, NonRollBackException;

	public ActionBean findAction(String namespace, String actionName) throws RollBackException, NonRollBackException;

}
