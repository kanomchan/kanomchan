package org.kanomchan.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kanomchan.core.common.bean.ActionBean;
import org.springframework.jdbc.core.RowMapper;

public class ActionDaoImpl extends JdbcCommonDaoImpl implements ActionDao {

	
	
	private RowMapper<String> rm = new RowMapper<String>() {
		
		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString(1);
		}
	};
	private static final String  sqlcode = "SELECT `ID_FUNCTION` FROM `SYS_M_ACTION` WHERE  `STATUS` = 'A' AND  `NAME_SPACE` = ? AND `ACTION_NAME` = ?";
	private static final String  sqlAction = "SELECT * FROM `SYS_M_ACTION` WHERE  `STATUS` = 'A' AND  `NAME_SPACE` = ? AND `ACTION_NAME` = ?";
	@Override
	public List<String> getAuthorizeCodeByAction(String namespace, String actionName) {
		
		return simpleJdbcTemplate.query(sqlcode, rm, namespace,actionName);
	}
	@Override
	public ActionBean findAction(String namespace, String actionName) {
		return nativeQueryOneRow(sqlAction, ActionBean.class, namespace,actionName);
	}

}
