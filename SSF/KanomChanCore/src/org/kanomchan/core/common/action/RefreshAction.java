package org.kanomchan.core.common.action;

import org.apache.struts2.dispatcher.Dispatcher;
import org.kanomchan.core.common.service.RefreshService;
import org.kanomchan.core.common.web.struts.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class RefreshAction extends BaseAction {

	private RefreshService  refreshService;
	
	@Autowired
	@Required
	public void setRefreshService(RefreshService refreshService) {
		this.refreshService = refreshService;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3817380441178881118L;

	@Override
	public String init() throws Exception {
		
		refreshService.refreshAll();
		return "json";
	}
	
	
	public String refreshXml() throws Exception {
		
		Dispatcher.getInstance().getConfigurationManager().reload();
		return "json";
	}

}
