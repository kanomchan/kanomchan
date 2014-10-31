package org.kanomchan.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.bean.PagingBean.ORDER_MODE;
import org.kanomchan.core.common.bean.PagingBean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

@Transactional
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
		
		return simpleJdbcTemplate.update( sql );
	}
	@Override
	public int executeNativeSQL(String sql, Object... params) {
		return simpleJdbcTemplate.update( sql, params );
	}
	@Override
	public int executeNativeSQL(String sql, Map<String, Object> params) {
		return simpleJdbcTemplate.update( sql, params );
	}
	
	@Override
	public int executeNativeSQLGetId(String sql, Object... params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
//		SqlParameterSource sd = new BeanPropertySqlParameterSource(params);
//		simpleJdbcTemplate.getJdbcOperations().update(psc, generatedKeyHolder);
//		return keyHolder.getKey().intValue();
		
		
//		ParsedSql parsedSql = getParsedSql(sql);
//		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, paramSource);
//		Object[] params = NamedParameterUtils.buildValueArray(parsedSql, paramSource, null);
//		int[] paramTypes = NamedParameterUtils.buildSqlTypeArray(parsedSql, paramSource);
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql);
//		if (keyColumnNames != null) {
//			pscf.setGeneratedKeysColumnNames(keyColumnNames);
//		}
//		else {
			pscf.setReturnGeneratedKeys(true);
//		}
		simpleJdbcTemplate.getJdbcOperations().update(pscf.newPreparedStatementCreator(params), keyHolder);
		return keyHolder.getKey().intValue();
//		return simpleJdbcTemplate.update( sql, params );
	}
	
	@Override
	public int executeNativeSQLGetId(String sql, EntityBean params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new BeanPropertySqlParameterSource(params);
		simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, sd , keyHolder);
		return keyHolder.getKey().intValue();
//		return simpleJdbcTemplate.update( sql, params );
	}
	
	@Override
	public int executeNativeSQLGetId(String sql, Map<String, Object> params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new MapSqlParameterSource(params);
		simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, sd , keyHolder);
		return keyHolder.getKey().intValue();
//		return simpleJdbcTemplate.update( sql, params );
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
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm)  {
		return simpleJdbcTemplate.queryForObject(sql, rm);
	}
	
	@Override
	public <T extends Object>  List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Object... params) {
		String countQuery = "Select count(*) from ("+sql+") data";
		
		Long totalRows = simpleJdbcTemplate.queryForLong(countQuery, params);
		pagingBean.setTotalRows(totalRows);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(sql);
		if(pagingBean.getOrderList()!=null&&pagingBean.getOrderList().size()>0){
			List<String> joinlist  = new ArrayList<String>();
			sb.append(" ORDER BY ");
			for (Order order  : pagingBean.getOrderList()) {
				
				sb.append(order.getOrderBy());
				if(order.getOrderMode().equals(ORDER_MODE.ASC)){
					joinlist.add(order.getOrderBy()+" ASC ");
				}else{
					joinlist.add(order.getOrderBy()+" DESC ");
				}
			}
			
			sb.append(" ORDER BY ");
			sb.append(Joiner.on(" , ").skipNulls().join(joinlist));
		}
		
		
		sb.append("LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getOffsetEnd());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm, params);
		return resultList;
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params) {
		String countQuery = "Select count(*) from ("+sql+") data";
		
		Long totalRows = simpleJdbcTemplate.queryForLong(countQuery, params);
		pagingBean.setTotalRows(totalRows);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(sql);
		if(pagingBean.getOrderList()!=null&&pagingBean.getOrderList().size()>0){
			List<String> joinlist  = new ArrayList<String>();
			sb.append(" ORDER BY ");
			for (Order order  : pagingBean.getOrderList()) {
				
				sb.append(order.getOrderBy());
				if(order.getOrderMode().equals(ORDER_MODE.ASC)){
					joinlist.add(order.getOrderBy()+" ASC ");
				}else{
					joinlist.add(order.getOrderBy()+" DESC ");
				}
			}
			
			sb.append(" ORDER BY ");
			sb.append(Joiner.on(" , ").skipNulls().join(joinlist));
		}
		
		
		sb.append("LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getOffsetEnd());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm, params);
		return resultList;
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm) {
		String countQuery = "Select count(*) from ("+sql+") data";
		
		Long totalRows = simpleJdbcTemplate.queryForLong(countQuery);
		pagingBean.setTotalRows(totalRows);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(sql);
		if(pagingBean.getOrderList()!=null&&pagingBean.getOrderList().size()>0){
			List<String> joinlist  = new ArrayList<String>();
			sb.append(" ORDER BY ");
			for (Order order  : pagingBean.getOrderList()) {
				
				sb.append(order.getOrderBy());
				if(order.getOrderMode().equals(ORDER_MODE.ASC)){
					joinlist.add(order.getOrderBy()+" ASC ");
				}else{
					joinlist.add(order.getOrderBy()+" DESC ");
				}
			}
			
			sb.append(" ORDER BY ");
			sb.append(Joiner.on(" , ").skipNulls().join(joinlist));
		}
		
		
		sb.append("LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getOffsetEnd());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm);
		return resultList;
	}
}
