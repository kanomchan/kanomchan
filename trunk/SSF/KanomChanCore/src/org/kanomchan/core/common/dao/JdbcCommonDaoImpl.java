package org.kanomchan.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcCommonDaoImpl implements JdbcCommonDao {

	
	protected SimpleJdbcTemplate simpleJdbcTemplate;
	@Required
	@Autowired
	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}
	
	protected static final RowMapper<String> STRING_MAPPER = new RowMapper<String>() {
		
		@Override
		public String mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return resultSet.getString(1);
		}
	};
//	executeNativeSQL
	@Override
	public int executeNativeSQL(String sql) {
		return simpleJdbcTemplate.update( sql);
	}
	@Override
	public int executeNativeSQL(String sql, Object... params) {
		return simpleJdbcTemplate.update( sql, params );
	}
	@Override
	public int executeNativeSQL(String sql, Map<String, Object> params) {
		return simpleJdbcTemplate.update( sql, params );
	}
	
//	executeNativeSQL
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ){
		return simpleJdbcTemplate.query(sql, rm);
	}
	
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ,Object... params){
		return simpleJdbcTemplate.query(sql, rm, params);
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ,Map<String, Object> params){
		return simpleJdbcTemplate.query(sql, rm, params);
	}
	@Override
	public <T extends Object> T nativeQueryOneRow( String sql, RowMapper<T> rm ,Object... params) {
		List<T> resultList = simpleJdbcTemplate.query(sql, rm, params);
		T result = null;
		if (resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
//		return simpleJdbcTemplate.queryForObject(sql, rm, params);
	}
	@Override
	public <T extends Object> T nativeQueryOneRow( String sql, RowMapper<T> rm ,Map<String, Object> params){
		List<T> resultList = simpleJdbcTemplate.query(sql, rm, params);
		T result = null;
		if (resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
//		return simpleJdbcTemplate.queryForObject(sql, rm, params);
	}

	@Override
	public <T> T nativeQueryOneRow(String sql, RowMapper<T> rm)  {
		return simpleJdbcTemplate.queryForObject(sql, rm);
	}
}
