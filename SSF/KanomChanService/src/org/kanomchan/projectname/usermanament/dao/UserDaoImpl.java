package org.kanomchan.projectname.usermanament.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackProcessException;
import org.kanomchan.core.openid.service.UserOpenDao;
import org.kanomchan.projectname.usermanament.bean.User;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoImpl extends JdbcCommonDaoImpl implements UserDao ,UserOpenDao {
	
	@Override
	public User findUserByUsername(String username) throws NonRollBackException , RollBackException {
		String sql = "SELECT * FROM SYS_M_USER WHERE USERNAME = ? ";
		LinkedList<Object> params = new LinkedList<Object>();
        params.add( username );
        User userBean = nativeQueryOneRow(sql, USER_MAP, params.toArray());
		if(userBean ==null)
			throw new RollBackProcessException(CommonMessageCode.COM0004);
		return userBean;
	}

	@Override
	public User findUserByIdUser(Long id) throws NonRollBackException, RollBackException {
		String sql = "SELECT * FROM SYS_M_USER WHERE ID_USER = ? ";
		LinkedList<Object> params = new LinkedList<Object>();
        params.add( id );
		User userBean = nativeQueryOneRow(sql, USER_MAP, params.toArray());
		if(userBean ==null)
			throw new RollBackProcessException(CommonMessageCode.COM0004);
		return userBean;
	}
	
//	private static final ComAuthorizedMapper<User> UserBean = new ComAuthorizedMapper<User>();
//	public static final class ComAuthorizedMapper<T extends User> implements RowMapper<User> {
//	    public User mapRow(ResultSet rs, int num)throws SQLException {
//	    	User user = new User();
//	    	user.setUserId(rs.getString("ID_USER"));
//	    	user.setUsername(rs.getString("USERNAME"));
//	    	user.setPassword(rs.getString("PASSWORD"));
//	    	user.setFirstName(rs.getString("FIRST_NAME"));
//	    	user.setMidName(rs.getString("MID_NAME"));
//	    	user.setLastName(rs.getString("LAST_NAME"));
//	    	user.setStatus(rs.getString("STATUS"));
//			return user;
//	    }
//    }
	
	private static final RowMapper<User> USER_MAP = new RowMapper<User>() {
		
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
	    	user.setUserId(rs.getString("ID_USER"));
	    	user.setUsername(rs.getString("USERNAME"));
	    	user.setPassword(rs.getString("PASSWORD"));
	    	user.setFirstName(rs.getString("FIRST_NAME"));
	    	user.setMidName(rs.getString("MID_NAME"));
	    	user.setLastName(rs.getString("LAST_NAME"));
	    	user.setStatus(rs.getString("STATUS"));
			return user;
		}
	};
	
//	RowMapper<User> USER_MAPPER = new RowMapper<User>(){
//		public User mapRow(ResultSet rs, int rowNum) throws SQLException {}{
//			User user = new User();
//	    	user.setUserId(rs.getString("ID"));
//	    	user.setUsername(rs.getString("USERNAME"));
//	    	user.setPassword(rs.getString("PASSWORD"));
//	    	user.setFirstName(rs.getString("FIRST_NAME"));
//	    	user.setMidName(rs.getString("MID_NAME"));
//	    	user.setLastName(rs.getString("LAST_NAME"));
//	    	user.setStatus(rs.getString("STATUS"));
//			return user;
//		}
//	}
	
	@Override
	public User addUser(User userBean) throws RollBackException, NonRollBackException {
		User user = (User) userBean;
		StringBuffer sb = new StringBuffer();
		StringBuffer sbs = new StringBuffer();
		ArrayList<Object> param = new ArrayList<Object>();
		sb.append("INSERT INTO SYS_M_USER (USERNAME,PASSWORD,ID_PREFIX_NAME,FIRST_NAME,MID_NAME,LAST_NAME,MAIDEN_SURNAME,STATUS,CREATE_DATE,CREATE_USER) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, 'A', ?, ?)");
		sbs.append("INSERT INTO SYS_M_USER (USERNAME,PASSWORD,ID_PREFIX_NAME,FIRST_NAME,MID_NAME,LAST_NAME,MAIDEN_SURNAME,STATUS,CREATE_DATE,CREATE_USER) "
				+ "VALUES(:username, :password, :prefixNameId, :firstName, :midName, :lastName, :maidenSurname, 'A', :createDate, :createUser)");
//		if(!user.getUsername().equals(""))
		param.add(user.getUserName());
//		if(!user.getPassword().equals(""))
		param.add(user.getPassword());
//		if(!user.getPrefixNameId().equals(""))
		param.add(user.getPrefixNameId());
//		if(!user.getFirstName().equals(""))
		param.add(user.getFirstName());
//		if(!user.getMidName().equals(""))
		param.add(user.getMidName());
//		if(!user.getLastName().equals(""))
		param.add(user.getLastName());
//		if(!user.getMaidenSurname().equals(""))
		param.add(user.getMaidenSurname());
		param.add(new Date());
		param.add(user.getUserName());
//		executeNativeSQL(sb.toString(), param.toArray());
//		user = nativeQueryOneRow(user, user.getClass(), param.toArray());
		Number id = executeNativeSQLGetId(sb.toString(), param.toArray());
//		int ids = executeNativeSQLGetId(sbs.toString(), user);
		user.setId(id.longValue());
			
//		super.save(user);
		return user;
	}

	@Override
	public User changePassword(User userBean) throws RollBackException, NonRollBackException {
//		return super.updateOnlyNotNullBasic(userBean);
		return null;
	}

	@Override
	public User update(User user) throws RollBackException, NonRollBackException {
//		return super.updateOnlyNotNullBasic(user);
		return null;
	}
	
}
