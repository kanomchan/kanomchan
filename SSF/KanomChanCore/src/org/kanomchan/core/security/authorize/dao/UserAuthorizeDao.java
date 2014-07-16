package org.kanomchan.core.security.authorize.dao;

import java.util.Set;

public interface UserAuthorizeDao {
	public Set<String> getUserRoles( String userId );
	public Set<String> getUserPrivileges( String userId );
	public Set<String> getUserPrivilegesByRoleId( String roleId );
}
