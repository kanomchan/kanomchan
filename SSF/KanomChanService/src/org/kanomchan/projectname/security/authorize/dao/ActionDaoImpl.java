package org.kanomchan.projectname.security.authorize.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.security.authorize.dao.ActionDao;
import org.springframework.jdbc.core.RowMapper;

import com.googlecode.ehcache.annotations.Cacheable;

public class ActionDaoImpl extends JdbcCommonDaoImpl implements ActionDao {
	

	@Override
	public ActionBean getActionByMenuId(Integer menuId) {
		return nativeQueryOneRow(SQL_ACTION_MENU_ID, ACTION_MAPPER,menuId);
	}
	
	@Override
	@Cacheable(cacheName = "getActionByActionId")
	public ActionBean getActionByActionId(Integer actionId) {
		return nativeQueryOneRow(SQL_ACTION_ACTION_ID, ACTION_MAPPER,actionId);
	}

	@Override
	public ActionBean getActionByNamespaceAndActionName(String namespace,
			String actionName) {
		return nativeQueryOneRow(SQL_ACTION_NAMESPACE_ACTION_NAME, ACTION_MAPPER,namespace,actionName);
	}

	
	private static final String SQL_ACTION_ACTION_ID = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE ID_ACTION = ? " ;
	
	private static final String SQL_ACTION_MENU_ID = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE ID_MENU = ? " ;
	
	private static final String SQL_ACTION_NAMESPACE_ACTION_NAME = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE NAME_SPACE = ? and ACTION_NAME = ? " ;
	
	protected static final RowMapper<ActionBean> ACTION_MAPPER = new RowMapper<ActionBean>() {
		
		@Override
		public ActionBean mapRow(ResultSet rs, int arg1) throws SQLException {
			ActionBean actionBean = new ActionBean();
			actionBean.setActionId(rs.getInt("ID_ACTION"));
			actionBean.setNameSpace(rs.getString("NAME_SPACE"));
			actionBean.setActionName(rs.getString("ACTION_NAME"));
			actionBean.setPageName(rs.getString("PAGE_NAME"));
			actionBean.setMenuId(rs.getInt("ID_MENU"));
			actionBean.setObjId(rs.getString("ID_FUNCTION"));
			actionBean.setType(rs.getString("TYPE"));
			actionBean.setUrl(rs.getString("URL"));
		
			return actionBean;
		}
	};

	@Override
//	@TriggersRemove(cacheName="getActionByActionId", removeAll=true)
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
