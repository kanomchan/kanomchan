package org.kanomchan.core.common.dao;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Column;

import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.bean.PagingBean.ORDER_MODE;
import org.kanomchan.core.common.bean.PagingBean.Order;
import org.kanomchan.core.common.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.StatementCreatorUtils;
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
import com.sun.xml.ws.rx.rm.protocol.wsrm200702.SequenceAcknowledgementElement.Final;

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
	public Number executeNativeSQLGetId(String sql, Object... params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		List<SqlParameter> types = new ArrayList<SqlParameter>();
		for (Object object : params) {
			int t = 0;
			if(object!=null)
			t = StatementCreatorUtils.javaTypeToSqlParameterType(object.getClass());
			types.add(new SqlParameter(t));
		}
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(sql, types);
		pscf.setReturnGeneratedKeys(true);
		simpleJdbcTemplate.getJdbcOperations().update(pscf.newPreparedStatementCreator(params), keyHolder);
		return keyHolder.getKey();
	}
	
	@Override
	public Number executeNativeSQLGetId(String sql, EntityBean params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new BeanPropertySqlParameterSource(params);
		simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, sd , keyHolder);
		return keyHolder.getKey();
//		return simpleJdbcTemplate.update( sql, params );
	}
	
	@Override
	public Number executeNativeSQLGetId(String sql, Map<String, Object> params) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new MapSqlParameterSource(params);
		simpleJdbcTemplate.getNamedParameterJdbcOperations().update(sql, sd , keyHolder);
		return keyHolder.getKey();
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
	
	public <T extends Object> T save(T t){
		ClassMapper classMapper =getClassMapper(t.getClass());
		
		
		return t;
	}
	
	
	private <T extends Object> ClassMapper getClassMapper(Class<?> class1){
		ClassMapper classMapper =mapClass.get(class1.getName());
		if(classMapper ==null){
//			ClassMapper classMapper = mapClass.get(class1.getName());
			if(classMapper==null){
				classMapper = new ClassMapper();
				
				for (Field field : class1.getFields()) {
					
					Column column = field.getAnnotation(Column.class);
					if(column!=null&&classMapper.containsKeyMethodSet(column.name())){
						try {
						Method methodSet = ClassUtil.findSetter(class1, field.getName());
						
						classMapper.putMethodSet(column.name(),methodSet);
						} catch (NoSuchFieldException | IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					field.
				}
				for (Method method : class1.getMethods()) {
					
					Column column = method.getAnnotation(Column.class);
					if(column!=null&&classMapper.containsKeyMethodSet(column.name())){
						try {
							// TODO  Fix Bug
						Method methodSet = ClassUtil.findSetter(class1, method.getName());
						classMapper.putMethodSet(column.name(),methodSet);
						} catch (NoSuchFieldException | IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					field.
				}
				mapClass.put(class1.getName(),classMapper);
			}
		}
		return classMapper;
	}
	
	

	private static Map<String, ClassMapper > mapClass = new ConcurrentHashMap<String,ClassMapper>();
	private <T extends Object> RowMapper<T> getRm(final Class<T> class1){
		return new RowMapper<T>() {

			@Override
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				T t = null;
				try {
					t = class1.newInstance();
					ClassMapper classMapper = getClassMapper(class1);
					ResultSetMetaData md = rs.getMetaData();
					for (int i = 0; i < md.getColumnCount(); i++) {
						String columnName = md.getColumnName(i);
						Method methodSet = classMapper.getMethodSet(columnName);
						
						try {
							methodSet.invoke(t, rs.getObject(columnName, methodSet.getParameterTypes()[0]));
						} catch (IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
					
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				return t;
			}
		};
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params){
		return simpleJdbcTemplate.query(sql, getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Map<String, Object> params) {
		return nativeQueryOneRow(sql, getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Object... params) {
		return nativeQueryOneRow(sql, getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1) {
		return nativeQueryOneRow(sql, getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, Map<String, Object> params) {
		return nativeQuery(sql, getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) {
		return nativeQuery(sql, getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Object... params) {
		return nativeQuery(sql, pagingBean, getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Map<String, Object> params) {
		return nativeQuery(sql, pagingBean, getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1) {
		return nativeQuery(sql, pagingBean, getRm(class1));
	}
	
}
