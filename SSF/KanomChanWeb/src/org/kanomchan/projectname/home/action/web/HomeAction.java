package org.kanomchan.projectname.home.action.web;

import java.util.List;

import org.kanomchan.core.common.web.struts.action.BaseAction;
import org.kanomchan.projectname.modulename.bean.UserMapRole;
import org.kanomchan.projectname.modulename.dao.EventNameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class HomeAction extends BaseAction {

	
	private EventNameDao eventNameDao;
	@Autowired
	@Required
	public void setEventNameDao(EventNameDao eventNameDao) {
		this.eventNameDao = eventNameDao;
	}
	private List<UserMapRole> list;
	@Override
	public String init() throws Exception {
		list = eventNameDao.findAll();
		return "home.init";
	}
	public List<UserMapRole> getList() {
		return list;
	}
	public void setList(List<UserMapRole> list) {
		this.list = list;
	}

}
