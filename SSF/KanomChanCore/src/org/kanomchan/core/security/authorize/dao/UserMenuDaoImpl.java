package org.kanomchan.core.security.authorize.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.security.authorize.bean.Menu;
import org.kanomchan.core.security.authorize.dao.UserMenuDao;
import org.springframework.jdbc.core.RowMapper;

public class UserMenuDaoImpl extends JdbcCommonDaoImpl implements UserMenuDao {
	private static final String SQL_MENU = 
			" SELECT ID_MENU ,NAME ,LEVEL ,ID_PARENT ,ID_ACTION ,ID_FUNCTION,SEQ ,MENU_TYPE FROM SYS_M_MENU WHERE STATUS = 'A' AND MENU_TYPE != 'N' ORDER BY LEVEL ,SEQ" ;
//	AND MENU_TYPE = 'T'
	@Override
	public List<Menu> findAll() throws NonRollBackException, RollBackException {
		return nativeQuery(SQL_MENU, MENU_MAPPER);
	}
	
	private static final String SQL_MENU_NAVIGATION = 
			" SELECT ID_MENU ,NAME ,LEVEL ,ID_PARENT ,ID_ACTION ,ID_FUNCTION,SEQ ,MENU_TYPE FROM SYS_M_MENU WHERE STATUS = 'A' ORDER BY LEVEL ,SEQ" ;
	@Override
	public List<Menu> findAllForNavigation() throws NonRollBackException, RollBackException {
		return nativeQuery(SQL_MENU_NAVIGATION, MENU_MAPPER);
	}
	
	private static final String SQL_MENU_ACTION = 
			" SELECT ID_MENU ,NAME ,LEVEL ,ID_PARENT ,ID_ACTION ,ID_FUNCTION,SEQ ,MENU_TYPE FROM SYS_M_MENU WHERE STATUS = 'A' and ID_ACTION = ? ORDER BY LEVEL ,SEQ" ;
	@Override
	public List<Menu> findAllByAction(Long actionId) throws NonRollBackException, RollBackException {
		return nativeQuery(SQL_MENU_ACTION, MENU_MAPPER,actionId);
	}
	
	private static final String SQL_ID = 
			" SELECT ID_MENU ,NAME ,LEVEL ,ID_PARENT ,ID_ACTION ,ID_FUNCTION,SEQ ,MENU_TYPE FROM SYS_M_MENU WHERE STATUS = 'A' and ID_MENU = ? ORDER BY LEVEL ,SEQ" ;
	@Override
	public List<Menu> findAllByMenuId(Long menuId)throws NonRollBackException, RollBackException {
		return nativeQuery(SQL_ID, MENU_MAPPER,menuId);
	}
	protected static final RowMapper<Menu> MENU_MAPPER = new RowMapper<Menu>() {
		
		@Override
		public Menu mapRow(ResultSet rs, int arg1) throws SQLException {
			Menu menu = new Menu();
			if(rs.getObject("ID_PARENT") == null){
				menu.setParentId(null);
			}else{
				menu.setParentId(rs.getLong("ID_PARENT"));
			}
			if(rs.getObject("LEVEL") == null){
				menu.setLevel(null);
			}else{
				menu.setLevel(rs.getLong("LEVEL"));
			}
			menu.setMenuId(rs.getLong("ID_MENU"));
			menu.setMenuName(rs.getString("NAME"));
			menu.setMenuType(rs.getString("MENU_TYPE"));
			menu.setObjId(rs.getString("ID_FUNCTION"));
			
			menu.setActionId(rs.getLong("ID_ACTION"));
		
			return menu;
		}
	};

}
