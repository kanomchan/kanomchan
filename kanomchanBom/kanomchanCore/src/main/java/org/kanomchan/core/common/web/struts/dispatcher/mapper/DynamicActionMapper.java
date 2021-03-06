package org.kanomchan.core.common.web.struts.dispatcher.mapper;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.RequestUtils;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.mapper.DefaultActionMapper;
import com.opensymphony.xwork2.config.ConfigurationManager;

public class DynamicActionMapper extends DefaultActionMapper {

	public ActionMapping getMapping(HttpServletRequest request, ConfigurationManager configManager) {
		return getActionMapper(request, configManager).getMapping(request, configManager);
	}

	private ActionMapper getActionMapper(HttpServletRequest request, ConfigurationManager configManager) {
		ActionMapping actionMapping = new ActionMapping();
		parseNameAndNamespace(RequestUtils.getUri(request), actionMapping, configManager);
		if (!(actionMapping.getNamespace()).contains("/rest")) {
			return container.getInstance(ActionMapper.class, "struts");
		} else {
			return container.getInstance(ActionMapper.class, "rest");
		}
	}
}
