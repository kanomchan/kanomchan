package org.kanomchan.core.common.service;

import java.util.Set;

import org.kanomchan.core.common.bean.ActionBean;

public interface ActionService {

	public Set<String> getAuthorizeCodeByAction(String namespace, String actionName);

	public ActionBean findAction(String namespace, String actionName);

}
