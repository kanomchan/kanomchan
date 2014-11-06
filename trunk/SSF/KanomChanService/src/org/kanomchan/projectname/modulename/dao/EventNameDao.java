package org.kanomchan.projectname.modulename.dao;

import java.util.List;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.projectname.modulename.bean.UserMapRole;

public interface EventNameDao {
	
	public List<UserMapRole> findAll() throws NonRollBackException, RollBackException;

}
