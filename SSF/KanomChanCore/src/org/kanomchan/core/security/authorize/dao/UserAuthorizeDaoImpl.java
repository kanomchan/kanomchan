package org.kanomchan.core.security.authorize.dao;

import java.util.HashSet;
import java.util.Set;

import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.security.authorize.dao.UserAuthorizeDao;

public class UserAuthorizeDaoImpl extends JdbcCommonDaoImpl implements UserAuthorizeDao {

	
	private static final String SQL_USER_ROLES =
			" SELECT ID_ROLE FROM SYS_M_USER_MAP_ROLE WHERE STATUS = 'A' AND ID_USER = ?" ;
	@Override
	public Set<String> getUserRoles(String userId) throws RollBackException, NonRollBackException {
		Set<String> roles = new HashSet<String>(nativeQuery( SQL_USER_ROLES, STRING_MAPPER,userId ) );
		return roles;
	}
	
	private static final String SQL_USER_PRIVILEGES = 
			" SELECT ID_FUNCTION FROM SYS_M_ROLE_MAP_FUNCTION po JOIN SYS_M_USER_MAP_ROLE ur ON ur.STATUS = 'A' AND po.ID_ROLE = ur.ID_ROLE AND ur.ID_USER = ?" ;

	@Override
	public Set<String> getUserPrivileges(String userId) throws RollBackException, NonRollBackException {
		Set<String> privileges = new HashSet<String>( nativeQuery( SQL_USER_PRIVILEGES, STRING_MAPPER, userId ) );
		return privileges;
	}
	private static final String SQL_USER_PRIVILEGES_BY_ROLE = 
			" SELECT ID_FUNCTION FROM SYS_M_ROLE_MAP_FUNCTION po WHERE STATUS = 'A' AND ID_ROLE = ? " ;

	@Override
	public Set<String> getUserPrivilegesByRoleId(String roleId) throws RollBackException, NonRollBackException {
		Set<String> privileges = new HashSet<String>( nativeQuery( SQL_USER_PRIVILEGES_BY_ROLE, STRING_MAPPER, roleId ) );
		return privileges;
	}

	

}
