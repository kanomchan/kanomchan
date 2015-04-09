package org.kanomchan.core.security.authorize.dao;

import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;

public interface ActionDao {
	
//	public ActionBean getAction(Integer menuId);

	public ActionBean getActionByActionId(Integer actionId);

	public ActionBean getActionByMenuId(Integer menuId);
	
	public ActionBean getActionByUrl(String url);
	
	public ActionBean getActionByNamespaceAndActionName(String namespace, String actionName);

	public void refresh();

	public List<ActionBean> getActionByUrlList(String url);

	public List<ActionBean> getActionByNamespaceAndActionNameList(String namespace,
			String actionName);

}
