package org.kanomchan.core.common.dao;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.Table;

import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.ColumnType;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.bean.PagingBean.ORDER_MODE;
import org.kanomchan.core.common.bean.PagingBean.Order;
import org.kanomchan.core.common.bean.Property;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

@Transactional
public class JdbcCommonDaoImpl implements JdbcCommonDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JdbcCommonDaoImpl.class);

	
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
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM "+str[1] : "Select count(*) from ("+sql+") data";
		
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
		
		
		sb.append(" LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getRowsPerPage());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm, params);
		return resultList;
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params) {
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM "+str[1] : "Select count(*) from ("+sql+") data";
		
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
		
		
		sb.append(" LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getRowsPerPage());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm, params);
		return resultList;
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm) {
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM "+str[1] : "Select count(*) from ("+sql+") data";
		
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
		
		
		sb.append(" LIMIT ");
		sb.append(pagingBean.getOffsetBegin());
		sb.append(" , ");
		sb.append(pagingBean.getRowsPerPage());
		
		List<T> resultList = simpleJdbcTemplate.query(sb.toString(), rm);
		return resultList;
	}
	
	public <T extends Object> T save(T target){
		return save(target, true);
	}
	
//	public <T extends EntityBean> T save(T target){
//		return save(target, true);
//	}
	
	public <T extends Object> T save(T target, boolean includeMinusOne){
		
		if(target instanceof EntityBean){
			ProcessContext processContext = CurrentThread.getProcessContext();
			EntityBean entityBean = (EntityBean) target;
			entityBean.setCreateDate(new Date());
			entityBean.setCreateUser(processContext.getUserName());
		}
		Class<? extends Object> clazz = target.getClass();
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		StringBuilder sb = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		
		sb.append(" INSERT INTO  ");
		sb.append(table.name());
//		sb.append(" SET ");
		List<Object> para = new LinkedList<Object>();
//		Method[] arrmet = clazz.getMethods();
		List<String> listColumnName = new LinkedList<String>();
		List<String> listParaName = new LinkedList<String>();
//		List<String> listPkName = new LinkedList<String>();
//		List<Object> listPkId = new LinkedList<Object>();
		Method methodSetId = classMapper.getPropertyId().getMethodSet();
		
		for (String  columnName : classMapper.getColumn().keySet()) {
			Property property = classMapper.getColumn().get(columnName).get(0);
			Method method = property.getMethodGet();
			try {
//				Column column = method.getAnnotation(Column.class);
//				Id id = method.getAnnotation(Id.class);
//				JoinColumn joinColumn = method.getAnnotation(JoinColumn.class);
//				AttributeOverrides attributeOverrides = method.getAnnotation(AttributeOverrides.class);
				if(property.getColumnType() == ColumnType.column){
					Object value = method.invoke(target);
					if(value != null){
//						listColumnName.add(columnName);
//						listParaName.add("?");
//						para.add(value);
						if(value instanceof Number){
							if(includeMinusOne || ((Number)value).intValue() !=-1){
								listColumnName.add(columnName);
								listParaName.add("?");
								if(((Number)value).longValue() == 0)
									value = null;
								para.add(value);
							}
						}else{
							if(includeMinusOne || (!value.equals("-1") && !"-1".equals(value))){
								listColumnName.add(columnName);
								listParaName.add("?");
								para.add(value);
							}
								
						}
					}
				}
				if(property.getColumnType() == ColumnType.joinColumn){
					Object value = method.invoke(target);
					if(value != null){
						Entity entity = method.getReturnType().getAnnotation(Entity.class);
						if(entity!=null){
							ClassMapper classMapperId = JPAUtil.getClassMapper(method.getReturnType());
							
							value = classMapperId.getPropertyId().getMethodGet().invoke(value);
//							if(value!=null){
//								listColumnName.add(columnName);
//								listParaName.add("?");
//								if((Long)value == 0)
//									value = null;
//								para.add(value);
//							}
							if(value!=null ) {
								if(value instanceof Number){
									if(includeMinusOne || ((Number)value).intValue() !=-1){
										listColumnName.add(columnName);
										listParaName.add("?");
										if((Long)value == 0)
											value = null;
										para.add(value);
									}
								}else{
									listColumnName.add(columnName);
									listParaName.add("?");
									if((Long)value == 0)
										value = null;
									para.add(value);
								}
							}
						}else{
							listColumnName.add(columnName);
							listParaName.add("?");
							para.add(value);
						}
						
					}
				}
				
				if(property.getColumnType() == ColumnType.id){
					Object value = method.invoke(target);
					if(value != null){
						listColumnName.add(columnName);
						listParaName.add("?");
						para.add(value);
					}
				}
				
				if(property.getColumnType() == ColumnType.embeddedId){
					Object valueEmbeddedId = method.invoke(target);
					if(valueEmbeddedId!=null){
						ClassMapper classMapperEmbeddedId= JPAUtil.getClassMapper(method.getReturnType());
//						classMapperEmbeddedId.getColumn();
						
						for (String  embeddedIdColumnName : classMapperEmbeddedId.getColumn().keySet()) {
							Property embeddedIdProperty = classMapperEmbeddedId.getColumn().get(columnName).get(0);
							Method embeddedIdMethod = embeddedIdProperty.getMethodGet();
							Object embeddedIdValue = embeddedIdMethod.invoke(valueEmbeddedId);
							if(embeddedIdValue != null){
								listColumnName.add(embeddedIdColumnName);
								listParaName.add("?");
								para.add(embeddedIdValue);
							}
						}
						
					}
					
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		sb.append(" ( ");
		sb.append(Joiner.on(" , ").skipNulls().join(listColumnName));
		sb.append(" ) VALUES ( ");
		sb.append(Joiner.on(" , ").skipNulls().join(listParaName));
		sb.append(" ) ");
//		if(listPkName.size() != 0){
//			sb.append(" WHERE ");
//			sb.append(Joiner.on(" AND ").skipNulls().join(listPkName));
//			for (Object id : listPkId) 
//				para.add(id);
			Number idNumber = executeNativeSQLGetId(sb.toString(),para.toArray());
			if(methodSetId !=null&&idNumber!=null){
				try {
					if(methodSetId.getParameterTypes()!=null&&methodSetId.getParameterTypes().length!=0){
						
						Class<?> type = methodSetId.getParameterTypes()[0];
						if(type == Long.class){
							methodSetId.invoke(target, idNumber.longValue());
						}else if(type == Integer.class){
							methodSetId.invoke(target, idNumber.intValue());
						}else if(type == Short.class){
							methodSetId.invoke(target, idNumber.shortValue());
						}else if(type == Double.class){
							methodSetId.invoke(target, idNumber.doubleValue());
						}

						
							
						
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//		}
		return target;
	}
	public <T extends Object> T saveOrUpdate(T t, boolean includeMinusOne){
		ClassMapper classMapper =JPAUtil.getClassMapper(t.getClass());
		Object objectId = null;
		try {
			objectId = classMapper.getPropertyId().getMethodGet().invoke(t);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			
		}
		if(objectId!=null){
			try{
				update(t, includeMinusOne);
			} catch (Exception e){
				save(t, includeMinusOne);
			}
			
		}
		else{
			save(t, includeMinusOne);
		}
		return t;
	}
	
	public <T extends Object> T saveOrUpdate(T t){
		ClassMapper classMapper =JPAUtil.getClassMapper(t.getClass());
		Object objectId = null;
		try {
			objectId = classMapper.getPropertyId().getMethodGet().invoke(t);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			
		}
		if(objectId!=null){
			try{
				update(t, true);
			} catch (Exception e){
				save(t, true);
			}
			
		}
		else{
			save(t, true);
		}
		return t;
	}
//	public <T extends Object> T update(T t){
//		return t;
//	}
	
	public <T extends Object> T update(T target) throws RollBackTechnicalException{
		return update(target, true);
	}
	
	public <T extends Object> T update(T target, boolean includeMinusOne) throws RollBackTechnicalException{
		if(target instanceof EntityBean){
			ProcessContext processContext = CurrentThread.getProcessContext();
			EntityBean entityBean = (EntityBean) target;
			entityBean.setUpdateDate(new Date());
			entityBean.setUpdateUser(processContext.getUserName());
		}
		Class<? extends Object> clazz = target.getClass();
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		StringBuilder sb = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		
		List<Object> para = new LinkedList<Object>();
		List<String> listPkName = new LinkedList<String>();
		List<Object> listPkNamePara = new LinkedList<Object>();
		List<String> listColumnName = new LinkedList<String>();
		List<String> listParaName = new LinkedList<String>();
//		Method methodSetId = classMapper.getPropertyId().getMethodSet();
		
		for (String  columnName : classMapper.getColumn().keySet()) {
			Property property = classMapper.getColumn().get(columnName).get(0);
			Method method = property.getMethodGet();
			try {
				if(property.getColumnType() == ColumnType.column){
					Object value = method.invoke(target);
//					if(value != null){
//						listColumnName.add(columnName);
//						listParaName.add("?");
//						para.add(value);
//					}
					if(value != null){
//						listColumnName.add(columnName);
//						listParaName.add("?");
//						para.add(value);
						if(value instanceof Number){
							if(includeMinusOne || ((Number)value).intValue() !=-1){
								listColumnName.add(columnName);
								listParaName.add("?");
								if(((Number)value).intValue() == 0)
									value = null;
								para.add(value);
							}
						}else{
							if(includeMinusOne || (!value.equals("-1") && !"-1".equals(value))){
								listColumnName.add(columnName);
								listParaName.add("?");
								para.add(value);
							}
								
						}
					}
				}
				if(property.getColumnType() == ColumnType.joinColumn){
					Object value = method.invoke(target);
					if(value != null){
						Entity entity = method.getReturnType().getAnnotation(Entity.class);
						if(entity!=null){
							ClassMapper classMapperId = JPAUtil.getClassMapper(method.getReturnType());
							
							value = classMapperId.getPropertyId().getMethodGet().invoke(value);
							
							if(value!=null ) {
								if(value instanceof Number){
									if(includeMinusOne || ((Number)value).intValue() !=-1){
										listColumnName.add(columnName);
										listParaName.add("?");
										if(((Number)value).intValue() == 0)
											value = null;
										para.add(value);
									}
								}else{
									listColumnName.add(columnName);
									listParaName.add("?");
									if("null".equalsIgnoreCase(value+"")||(value+"").equals("0"))
										value = null;
									para.add(value);
								}
							}
						}else{
							listColumnName.add(columnName);
							listParaName.add("?");
							para.add(value);
						}
					}
				}
				if(property.getColumnType() == ColumnType.id){
					Object value = method.invoke(target);
					if(value != null){
						listPkName.add(columnName);
						listPkNamePara.add(value);
//						listColumnName.add(columnName);
//						listParaName.add("?");
//						para.add(value);
					}
				}
				
				if(property.getColumnType() == ColumnType.embeddedId){
					Object valueEmbeddedId = method.invoke(target);
					if(valueEmbeddedId!=null){
						ClassMapper classMapperEmbeddedId= JPAUtil.getClassMapper(method.getReturnType());
//						classMapperEmbeddedId.getColumn();
						
						for (String  embeddedIdColumnName : classMapperEmbeddedId.getColumn().keySet()) {
							Property embeddedIdProperty = classMapperEmbeddedId.getColumn().get(columnName).get(0);
							Method embeddedIdMethod = embeddedIdProperty.getMethodGet();
							Object embeddedIdValue = embeddedIdMethod.invoke(valueEmbeddedId);
							if(embeddedIdValue != null){
								listPkName.add(embeddedIdColumnName);
//								listParaName.add("?");
								listPkNamePara.add(embeddedIdValue);
							}
						}
						
					}
					
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("update(T, boolean)", e); //$NON-NLS-1$
			}
		}
		
		sb.append(" UPDATE ");
		sb.append(table.name());
		sb.append(" SET ");
//		sb.append(" ( ");
		for (int i = 0; i < listColumnName.size(); i++) {
			sb.append( i > 0 ? ",":"" );
			sb.append(listColumnName.get(i));
			sb.append(" = ");
			sb.append(listParaName.get(i));
		}
//		sb.append(" ) ");
		
		if(listPkName.size() > 0){
			sb.append(" WHERE ");
			for (int i = 0; i < listPkName.size(); i++) {
				sb.append( i > 0 ? ",":"" );
				sb.append(listPkName.get(i));
				sb.append(" = ? ");
				para.add(listPkNamePara.get(i));
//				sb.append(listPkNamePara.get(i));
			}
		}
		
		if(listColumnName.size() > 0&&listPkName.size() > 0){
			int row = executeNativeSQL(sb.toString(),para.toArray());
			if(row == 0){
				throw new RollBackTechnicalException(CommonMessageCode.COM4993);
			}
//			System.out.println(idNumber);
//			if(methodSetId !=null&&idNumber!=null){
//				try {
//					if(methodSetId.getParameterTypes()!=null&&methodSetId.getParameterTypes().length!=0){
//						
//						Class<?> type = methodSetId.getPar6ameterTypes()[0];
//						if(type == Long.class){
//							methodSetId.invoke(target, idNumber.longValue());
//						}else if(type == Integer.class){
//							methodSetId.invoke(target, idNumber.intValue());
//						}else if(type == Short.class){
//							methodSetId.invoke(target, idNumber.shortValue());
//						}else if(type == Double.class){
//							methodSetId.invoke(target, idNumber.doubleValue());
//						}
//					}
//				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//					logger.error("update(T, boolean)", e); //$NON-NLS-1$
//				}
//			}
		}else{
			if (logger.isDebugEnabled()) {
				logger.debug("update(T, boolean) - column size 0"); //$NON-NLS-1$
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993);

		}
		
		return target;
	}
	
	public <T extends Object> T delete(T target){
		return target;
	}
	public <T extends Object> T updateStatusDelete(T target) {
		Class<? extends Object> clazz = target.getClass();
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		StringBuilder sb = new StringBuilder();
		Table table = clazz.getAnnotation(Table.class);
		
		List<Object> para = new LinkedList<Object>();
		List<String> listPkName = new LinkedList<String>();
		List<Object> listPkNamePara = new LinkedList<Object>();
		Method methodSetId = classMapper.getPropertyId().getMethodSet();
		
		for (String  columnName : classMapper.getColumn().keySet()) {
			Property property = classMapper.getColumn().get(columnName).get(0);
			Method method = property.getMethodGet();
			try {
				if(property.getColumnType() == ColumnType.id){
					Object value = method.invoke(target);
					if(value != null){
						listPkName.add(columnName);
						listPkNamePara.add(value);
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		sb.append(" UPDATE ");
		sb.append(table.name());
		sb.append(" SET STATUS = ? ");
		para.add("I");
		if(listPkName.size() > 0){
			sb.append(" WHERE ");
			for (int i = 0; i < listPkName.size(); i++) {
				sb.append( i > 0 ? ",":"" );
				sb.append(listPkName.get(i));
				sb.append(" = ? ");
				para.add(listPkNamePara.get(i));
			}
		}else{
			// throw exception not parameter
			throw new RollbackException(CommonMessageCode.COM4991.toString());
		}

		Number idNumber = executeNativeSQLGetId(sb.toString(),para.toArray());
		if(methodSetId !=null&&idNumber!=null){
			try {
				if(methodSetId.getParameterTypes()!=null&&methodSetId.getParameterTypes().length!=0){
					
					Class<?> type = methodSetId.getParameterTypes()[0];
					if(type == Long.class){
						methodSetId.invoke(target, idNumber.longValue());
					}else if(type == Integer.class){
						methodSetId.invoke(target, idNumber.intValue());
					}else if(type == Short.class){
						methodSetId.invoke(target, idNumber.shortValue());
					}else if(type == Double.class){
						methodSetId.invoke(target, idNumber.doubleValue());
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return target;
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params){
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Map<String, Object> params) {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Object... params) {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1) {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, Map<String, Object> params) {
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) {
		return nativeQuery(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Object... params) {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Map<String, Object> params) {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1) {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1));
	}
	@Override
	public <T> T updateOnlyNotNullBasic(T target) throws RollBackTechnicalException {
		return update(target);
	}
	@Override
	public <T> T get(Serializable target,  Class<T> clazz) {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		Property property = classMapper.getPropertyId();
		String sql = "select * from " + classMapper.getTableName() + " where " + property.getColumnName() + " = ?";
		return nativeQueryOneRow(sql , JPAUtil.getRm(clazz), target);
	}
//	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		String sql = "select * from " + classMapper.getTableName();
		return nativeQuery(sql, clazz);
	}
	
	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value) throws RollBackTechnicalException {
		return findByColumn(clazz, propertyName, value,null);
	}
	
	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackTechnicalException {
		if(pagingBean==null){
			try{
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				String queryString = genQueryStringByExample(clazz, criteriaList, null,null, false);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = simpleJdbcTemplate.query(queryString, JPAUtil.getRm(clazz), params);
				return resultList1;
			}catch(RuntimeException e){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
			}
			
		}else{
			try{
				
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				
				pagingBean.setTotalRows(getTotalRowByExample(clazz, criteriaList, null, false));
				
				String qureyString = genQueryStringByExample(clazz, criteriaList, pagingBean,null, false);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = simpleJdbcTemplate.query(qureyString, JPAUtil.getRm(clazz), params);
				
				return resultList1;
				
			}catch (RuntimeException re){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, re);
			}
		}
	}
	protected String genQueryStringByExample(Class<?> clazz,List<Criteria> criteriaList,PagingBean pagingBean,String extraWhereClause,boolean like){
		
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append(FROM);
		countQueryString.append(JPAUtil.getClassMapper(clazz).getTableName());
		countQueryString.append(AILIAT);
		
		countQueryString.append(genQueryWhereStringByECriteria(criteriaList, extraWhereClause, like));
		
//		sb.append(sql);
		if(pagingBean !=null){
			countQueryString.append(genQueryOrderStringByOrders(pagingBean.getOrderList()));
//			
			countQueryString.append("LIMIT ");
			countQueryString.append(pagingBean.getOffsetBegin());
			countQueryString.append(" , ");
			countQueryString.append(pagingBean.getRowsPerPage());
		}

		return countQueryString.toString();
	}
	
	protected Long getTotalRowByExample(Class<?> clazz,List<Criteria> criteriaList,String extraWhereClause,boolean like) {
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append("select count(*) from ");
		countQueryString.append(JPAUtil.getClassMapper(clazz).getTableName());
		countQueryString.append(AILIAT);
		
		countQueryString.append(genQueryWhereStringByECriteria(criteriaList, extraWhereClause, like));
		Map<String, Object>params = new HashMap<String, Object>();
		if (criteriaList != null && criteriaList.size() > 0) {
			for (Criteria criteria : criteriaList) {
				params.put(criteria.getParam(), criteria.getValue());
			}
		}
		Long totalRows = simpleJdbcTemplate.queryForLong(countQueryString.toString(), params);
		return totalRows;
	}
	
	protected String genQueryWhereStringByECriteria(List<Criteria> criteriaList,String extraWhereClause,boolean like) {
		StringBuilder queryString = new StringBuilder();
		
		if(criteriaList !=null && criteriaList.size()>0){
			queryString.append(WHERE);
			List<String> criteriaString = new ArrayList<String>();
			for (Criteria criteria : criteriaList) {
				StringBuilder sb = new StringBuilder();
				if(criteria.getValue() instanceof String && like){
					sb.append(UPPER);
					sb.append(criteria.getColumn());
					sb.append(LIKE);
					sb.append(criteria.getParam());
				}else{
					sb.append(NON);
					sb.append(criteria.getColumn());
					sb.append(EQU);
					sb.append(criteria.getParam());
				}
				criteriaString.add(sb.toString());
			}
			queryString.append(Joiner.on(" AND ").skipNulls().join(criteriaString));
		}
		
		if(extraWhereClause!=null &&extraWhereClause.length()>0){
			if (criteriaList==null||criteriaList.size()>0) {
				queryString.append(extraWhereClause);
			}
		}
		
		return queryString.toString();
	}
	protected String genQueryOrderStringByOrders(List<Order> orderList) {
		StringBuilder sb = new StringBuilder();
		if(orderList!=null&&orderList.size()>0){
			List<String> joinlist  = new ArrayList<String>();
//			sb.append(ORDER);
			for (Order order  : orderList) {
				
				sb.append(order.getOrderBy());
				if(order.getOrderMode().equals(ORDER_MODE.ASC)){
					joinlist.add(NON+order.getOrderBy()+" ASC ");
				}else{
					joinlist.add(NON+order.getOrderBy()+" DESC ");
				}
			}
			
			sb.append(ORDER);
			sb.append(Joiner.on(" , ").skipNulls().join(joinlist));
		}
		return sb.toString();
	}
//	genQueryOrderStringByOrders
	
	
	private static final String LIKE = ") like :"; 
	private static final String NON = CommonDao.ENTITY_MODEL_ALIAS+"."; 
	private static final String UPPER = " UPPER("+CommonDao.ENTITY_MODEL_ALIAS+"."; 
	private static final String EQU = " = :"; 
	private static final String WHERE = " where "; 
	private static final String FROM = "select "+CommonDao.ENTITY_MODEL_ALIAS+".* from "; 
	private static final String ORDER = " order by "; 
	private static final String AILIAT = " "+CommonDao.ENTITY_MODEL_ALIAS+" "; 
	
	public void saveOrUpdateAll(final Collection entities){
		for (Object entity : entities) {
			saveOrUpdate(entity);
		}
	}
}
