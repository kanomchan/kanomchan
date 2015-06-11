package org.kanomchan.core.security.authorize.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;
import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.security.authorize.dao.ActionDao;
import org.springframework.jdbc.core.RowMapper;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

public class ActionDaoImpl extends JdbcCommonDaoImpl implements ActionDao {
	

	@Override
	@Cacheable(cacheName = "actionDao.getActionByMenuId")
	public ActionBean getActionByMenuId(Long menuId) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(SQL_ACTION_MENU_ID, ACTION_MAPPER,menuId);
	}
	
	@Override
	@Cacheable(cacheName = "actionDao.getActionByActionId")
	public ActionBean getActionByActionId(Long actionId) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(SQL_ACTION_ACTION_ID, ACTION_MAPPER,actionId);
	}

	@Override
	@Cacheable(cacheName = "actionDao.getActionByNamespaceAndActionName")
	public ActionBean getActionByNamespaceAndActionName(String namespace,
			String actionName) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(SQL_ACTION_NAMESPACE_ACTION_NAME, ACTION_MAPPER,namespace,actionName);
	}

	
	private static final String SQL_ACTION_ACTION_ID = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE ID_ACTION = ? AND STATUS = 'A' " ;
	
	private static final String SQL_ACTION_MENU_ID = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE ID_MENU = ? AND STATUS = 'A' " ;
	
	private static final String SQL_ACTION_NAMESPACE_ACTION_NAME = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE TYPE = 'A' AND NAME_SPACE = ? and ACTION_NAME = ? AND STATUS = 'A' " ;
	
	private static final String SQL_ACTION_URL = " SELECT ID_ACTION ,PAGE_NAME ,ACTION_NAME ,NAME_SPACE ,URL ,TYPE ,ID_MENU ,ID_FUNCTION  FROM SYS_M_ACTION WHERE TYPE = 'U' AND URL = ? AND STATUS = 'A' " ;
	
	protected static final RowMapper<ActionBean> ACTION_MAPPER = new RowMapper<ActionBean>() {
		
		@Override
		public ActionBean mapRow(ResultSet rs, int arg1) throws SQLException {
			ActionBean actionBean = new ActionBean();
			actionBean.setActionId(rs.getLong("ID_ACTION"));
			actionBean.setNameSpace(rs.getString("NAME_SPACE"));
			actionBean.setActionName(rs.getString("ACTION_NAME"));
			actionBean.setPageName(rs.getString("PAGE_NAME"));
			actionBean.setMenuId(rs.getLong("ID_MENU"));
			actionBean.setObjId(rs.getString("ID_FUNCTION"));
			actionBean.setType(rs.getString("TYPE"));
			actionBean.setUrl(rs.getString("URL"));
		
			return actionBean;
		}
	};

	@Override
	@TriggersRemove(cacheName={"actionDao.getActionByMenuId","actionDao.getActionByActionId","actionDao.getActionByNamespaceAndActionName","actionDao.getActionByUrl","actionDao.getActionByUrlList","actionDao.getActionByNamespaceAndActionNameList"}, removeAll=true)
	public void refresh() {
	}

	@Override
	@Cacheable(cacheName = "actionDao.getActionByUrl")
	public ActionBean getActionByUrl(String url) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(SQL_ACTION_URL, ACTION_MAPPER,url);
	}

	@Override
	@Cacheable(cacheName = "actionDao.getActionByUrlList")
	public List<ActionBean> getActionByUrlList(String url) throws RollBackException, NonRollBackException {
		return nativeQuery(SQL_ACTION_URL, ACTION_MAPPER,url);
	}

	@Override
	@Cacheable(cacheName = "actionDao.getActionByNamespaceAndActionNameList")
	public List<ActionBean> getActionByNamespaceAndActionNameList(String namespace, String actionName) throws RollBackException, NonRollBackException {
		return nativeQuery(SQL_ACTION_NAMESPACE_ACTION_NAME, ACTION_MAPPER,namespace,actionName);
	}
	private RowMapper<String> rm = new RowMapper<String>() {
		
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString(1);
		}
	};
	private static final String  sqlcode = "SELECT `ID_FUNCTION` FROM `SYS_M_ACTION` WHERE  `STATUS` = 'A' AND  `NAME_SPACE` = ? AND `ACTION_NAME` = ?";
	private static final String  sqlAction = "SELECT * FROM `SYS_M_ACTION` WHERE  `STATUS` = 'A' AND  `NAME_SPACE` = ? AND `ACTION_NAME` = ?";
	@Override
	public List<String> getAuthorizeCodeByAction(String namespace, String actionName) throws RollBackException, NonRollBackException {
		
		return nativeQuery(sqlcode, rm, namespace,actionName);
	}
	@Override
	public ActionBean findAction(String namespace, String actionName) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(sqlAction, ActionBean.class, namespace,actionName);
	}
}
