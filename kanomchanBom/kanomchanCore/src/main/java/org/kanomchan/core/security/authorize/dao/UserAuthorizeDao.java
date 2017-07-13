package org.kanomchan.core.security.authorize.dao;

import java.util.Set;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface UserAuthorizeDao {
	public Set<String> getUserRoles( String userId ) throws RollBackException, NonRollBackException;
	public Set<String> getUserPrivileges( String userId ) throws RollBackException, NonRollBackException;
	public Set<String> getUserPrivilegesByRoleId( String roleId ) throws RollBackException, NonRollBackException;
}
