package org.kanomchan.core.common.dao;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.springframework.jdbc.core.RowMapper;

public interface JdbcCommonDao {
	
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Map<String, Object> params);

	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Object... params);
	
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm);

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Map<String, Object> params);

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Object... params);
	
	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Object... params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm);

	public int executeNativeSQL(String sql, Map<String, Object> params);

	public int executeNativeSQL(String sql, Object... params);

	public int executeNativeSQL(String sql);

	public int executeNativeSQLGetId(String sql, Object... params);

	public int executeNativeSQLGetId(String sql, Map<String, Object> params);

	public int executeNativeSQLGetId(String sql, EntityBean object);

	

}
