package org.kanomchan.projectname.modulename.dao;

import java.util.List;

import org.kanomchan.core.common.dao.CommonDaoImpl;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.projectname.modulename.bean.UserMapRole;

public class EventNameDaoImpl extends CommonDaoImpl implements EventNameDao {

	@Override
	public List<UserMapRole> findAll() throws NonRollBackException, RollBackException {
		return findAll(UserMapRole.class);
	}

}
