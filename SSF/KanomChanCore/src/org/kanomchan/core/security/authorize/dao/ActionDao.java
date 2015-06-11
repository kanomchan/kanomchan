package org.kanomchan.core.security.authorize.dao;

import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface ActionDao {
	
//	public ActionBean getAction(Integer menuId);

	public ActionBean getActionByActionId(Long actionId) throws RollBackException, NonRollBackException;

	public ActionBean getActionByMenuId(Long menuId) throws RollBackException, NonRollBackException;
	
	public ActionBean getActionByUrl(String url) throws RollBackException, NonRollBackException;
	
	public ActionBean getActionByNamespaceAndActionName(String namespace, String actionName) throws RollBackException, NonRollBackException;

	public void refresh();

	public List<ActionBean> getActionByUrlList(String url) throws RollBackException, NonRollBackException;

	public List<ActionBean> getActionByNamespaceAndActionNameList(String namespace,
			String actionName) throws RollBackException, NonRollBackException;
	
	public List<String> getAuthorizeCodeByAction(String namespace, String actionName) throws RollBackException, NonRollBackException;

	public ActionBean findAction(String namespace, String actionName) throws RollBackException, NonRollBackException;
}
