package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
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
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.util.ClassUtil;
import org.kanomchan.core.common.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

	
//	protected SimpleJdbcTemplate simpleJdbcTemplate;
//	@Required
//	@Autowired
//	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
//		this.simpleJdbcTemplate = simpleJdbcTemplate;
//	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected  NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private ConfigService configService;
	protected static final RowMapper<String> STRING_MAPPER = new RowMapper<String>() {
		
		@Override
		public String mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return resultSet.getString(1);
		}
	};
	
	protected static final RowMapper<Long> LONG_MAPPER = new RowMapper<Long>() {
		
		@Override
		public Long mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return resultSet.getLong(1);
		}
	};
	
	protected static final String SQL_COUNTY_LEFT_JOIN = new StringBuilder()
		.append(" LEFT JOIN SYS_M_COUNTY COUNTY{map} ON ")
			.append(" COUNTY{map}.ID_COUNTY = {prefix}.ID_COUNTY{subfix} ")
			.append(" AND COUNTY{map}.ID_CITY = {prefix}.ID_CITY{subfix} ")
			.append(" AND COUNTY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
			.append(" AND COUNTY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
		.append(" LEFT JOIN SYS_M_CITY CITY{map} ON  ")
			.append(" CITY{map}.ID_CITY = {prefix}.ID_CITY{subfix}  ")
			.append(" AND CITY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
			.append(" AND CITY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
		.append(" LEFT JOIN SYS_M_PROVINCE PROVINCE{map} ON   ")
			.append(" PROVINCE{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
			.append(" AND PROVINCE{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
		.append(" LEFT JOIN SYS_M_COUNTRY COUNTRY{map}  ON ")
			.append("COUNTRY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
		.toString();
	
	protected static final String SQL_COUNTY_SELECT = ",COUNTRY.* ,PROVINCE.*,CITY.* , COUNTY.* ";
	@Deprecated
	protected static String GEN_SQL_COUNTY_LEFT_JOIN(String mapTable,String colunmSubfix,String countySubfix){
		return new StringBuilder(SQL_COUNTY_LEFT_JOIN).toString().replaceAll("\\{prefix\\}", mapTable).replaceAll("\\{subfix\\}", colunmSubfix).replaceAll("\\{map\\}", countySubfix);
	}
	
	protected static final String SQL_COUNTY_LEFT_JOIN_LANG = new StringBuilder()
	.append(" LEFT JOIN SYS_M_COUNTY COUNTY{map} ON ")
		.append(" COUNTY{map}.ID_COUNTY = {prefix}.ID_COUNTY{subfix} ")
		.append(" AND COUNTY{map}.ID_CITY = {prefix}.ID_CITY{subfix} ")
		.append(" AND COUNTY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND COUNTY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_COUNTY_LANG COUNTY_LANG{map} ON ")
		.append(" COUNTY{map}.ID_COUNTY = COUNTY_LANG{map}.ID_COUNTY ")
		.append(" AND COUNTY{map}.ID_CITY = COUNTY_LANG{map}.ID_CITY ")
		.append(" AND COUNTY{map}.ID_PROVINCE = COUNTY_LANG{map}.ID_PROVINCE ")
		.append(" AND COUNTY{map}.ID_COUNTRY = COUNTY_LANG{map}.ID_COUNTRY ")
		.append(" AND COUNTY_LANG{map}.LANG_CODE3 = ? ")
		
	.append(" LEFT JOIN SYS_M_CITY CITY{map} ON  ")
		.append(" CITY{map}.ID_CITY = {prefix}.ID_CITY{subfix}  ")
		.append(" AND CITY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND CITY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_CITY_LANG CITY_LANG{map} ON  ")
		.append(" CITY{map}.ID_CITY = CITY_LANG{map}.ID_CITY  ")
		.append(" AND CITY{map}.ID_PROVINCE = CITY_LANG{map}.ID_PROVINCE ")
		.append(" AND CITY{map}.ID_COUNTRY = CITY_LANG{map}.ID_COUNTRY ")
		.append(" AND CITY_LANG{map}.LANG_CODE3 = ? ")
		
	.append(" LEFT JOIN SYS_M_PROVINCE PROVINCE{map} ON   ")
		.append(" PROVINCE{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND PROVINCE{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_PROVINCE_LANG PROVINCE_LANG{map} ON   ")
		.append(" PROVINCE{map}.ID_PROVINCE = PROVINCE_LANG{map}.ID_PROVINCE ")
		.append(" AND PROVINCE{map}.ID_COUNTRY = PROVINCE_LANG{map}.ID_COUNTRY ")
		.append(" AND PROVINCE_LANG{map}.LANG_CODE3 = ? ")
		
	.append(" LEFT JOIN SYS_M_COUNTRY COUNTRY{map}  ON ")
		.append("COUNTRY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_COUNTRY_LANG COUNTRY_LANG{map}  ON ")
		.append("COUNTRY{map}.ID_COUNTRY = COUNTRY_LANG{map}.ID_COUNTRY ")
		.append(" AND COUNTRY_LANG{map}.LANG_CODE3 = ? ")
	.toString();

	protected static final String SQL_COUNTY_SELECT_LANG = ",COUNTRY.* ,PROVINCE.* ,CITY.* , COUNTY.* ";
	protected static String GEN_SQL_COUNTY_LEFT_JOIN_LANG(String mapTable,String colunmSubfix,String countySubfix){
		return new StringBuilder(SQL_COUNTY_LEFT_JOIN_LANG).toString().replaceAll("\\{prefix\\}", mapTable).replaceAll("\\{subfix\\}", colunmSubfix).replaceAll("\\{map\\}", countySubfix);
	}
	protected static final String SQL_COUNTY_LEFT_JOIN_LANG_NAME = new StringBuilder()
	.append(" LEFT JOIN SYS_M_COUNTY COUNTY{map} ON ")
		.append(" COUNTY{map}.ID_COUNTY = {prefix}.ID_COUNTY{subfix} ")
		.append(" AND COUNTY{map}.ID_CITY = {prefix}.ID_CITY{subfix} ")
		.append(" AND COUNTY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND COUNTY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_COUNTY_LANG COUNTY_LANG{map} ON ")
		.append(" COUNTY{map}.ID_COUNTY = COUNTY_LANG{map}.ID_COUNTY ")
		.append(" AND COUNTY{map}.ID_CITY = COUNTY_LANG{map}.ID_CITY ")
		.append(" AND COUNTY{map}.ID_PROVINCE = COUNTY_LANG{map}.ID_PROVINCE ")
		.append(" AND COUNTY{map}.ID_COUNTRY = COUNTY_LANG{map}.ID_COUNTRY ")
		.append(" AND COUNTY_LANG{map}.LANG_CODE3 = :LANG ")
		
	.append(" LEFT JOIN SYS_M_CITY CITY{map} ON  ")
		.append(" CITY{map}.ID_CITY = {prefix}.ID_CITY{subfix}  ")
		.append(" AND CITY{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND CITY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_CITY_LANG CITY_LANG{map} ON  ")
		.append(" CITY{map}.ID_CITY = CITY_LANG{map}.ID_CITY  ")
		.append(" AND CITY{map}.ID_PROVINCE = CITY_LANG{map}.ID_PROVINCE ")
		.append(" AND CITY{map}.ID_COUNTRY = CITY_LANG{map}.ID_COUNTRY ")
		.append(" AND CITY_LANG{map}.LANG_CODE3 = :LANG ")
		
	.append(" LEFT JOIN SYS_M_PROVINCE PROVINCE{map} ON   ")
		.append(" PROVINCE{map}.ID_PROVINCE = {prefix}.ID_PROVINCE{subfix} ")
		.append(" AND PROVINCE{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_PROVINCE_LANG PROVINCE_LANG{map} ON   ")
		.append(" PROVINCE{map}.ID_PROVINCE = PROVINCE_LANG{map}.ID_PROVINCE ")
		.append(" AND PROVINCE{map}.ID_COUNTRY = PROVINCE_LANG{map}.ID_COUNTRY ")
		.append(" AND PROVINCE_LANG{map}.LANG_CODE3 = :LANG ")
		
	.append(" LEFT JOIN SYS_M_COUNTRY COUNTRY{map}  ON ")
		.append("COUNTRY{map}.ID_COUNTRY = {prefix}.ID_COUNTRY{subfix} ")
	.append(" LEFT JOIN SYS_M_COUNTRY_LANG COUNTRY_LANG{map}  ON ")
		.append(" COUNTRY{map}.ID_COUNTRY = COUNTRY_LANG{map}.ID_COUNTRY ")
		.append(" AND COUNTRY_LANG{map}.LANG_CODE3 = :LANG ")
	.toString();

	protected static final String SQL_COUNTY_SELECT_LANG_NAME = ",COUNTRY.* ,PROVINCE.* ,CITY.* , COUNTY.* ";
	protected static String GEN_SQL_COUNTY_LEFT_JOIN_LANG_NAME(String mapTable,String colunmSubfix,String countySubfix){
		return new StringBuilder(SQL_COUNTY_LEFT_JOIN_LANG_NAME).toString().replaceAll("\\{prefix\\}", mapTable).replaceAll("\\{subfix\\}", colunmSubfix).replaceAll("\\{map\\}", countySubfix);
	}
	@Override
	public int executeNativeSQL(String sql) throws RollBackException, NonRollBackException {
		return jdbcTemplate.update( sql );
	}
	
    /**
     * this method will return how many rows which affected from the update
     * method เธ�เธตเน� เธ�เธฐ return เธ�เธณเธ�เธงเธ� rows เธ—เธตเน�เธ–เธนเธ� update
     */
	@Override
	public int executeNativeSQL(String sql, Object... params) throws RollBackException, NonRollBackException {
		return jdbcTemplate.update( sql, params );
	}
	@Override
	public int executeNativeSQL(String sql, Map<String, Object> params) throws RollBackException, NonRollBackException {
		return namedParameterJdbcTemplate.update( sql, params );
	}
	/**
     * this method will return primary key of affected row
     */
	@Override
	public Number executeNativeSQLGetId(String sql, Object... params) throws RollBackException, NonRollBackException {
		KeyHolder keyHolder = executeNativeSQLGetIdKeyHolder(sql,params);
		return keyHolder.getKey();
	}
	@Override
	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, Object... params) throws RollBackException, NonRollBackException {
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
		jdbcTemplate.update(pscf.newPreparedStatementCreator(params), keyHolder);
		return keyHolder;
	}
	@Override
	public Number executeNativeSQLGetId(String sql, EntityBean params) throws RollBackException, NonRollBackException {
		KeyHolder keyHolder = executeNativeSQLGetIdKeyHolder(sql, params);
		return keyHolder.getKey();
	}
	
	@Override
	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, EntityBean params) throws RollBackException, NonRollBackException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new BeanPropertySqlParameterSource(params);
		jdbcTemplate.update(sql, sd , keyHolder);
		return keyHolder;
	}
	
	@Override
	public Number executeNativeSQLGetId(String sql, Map<String, Object> params) throws RollBackException, NonRollBackException {
		KeyHolder keyHolder = executeNativeSQLGetIdKeyHolder(sql, params);
		return keyHolder.getKey();
	}
	
	@Override
	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, Map<String, Object> params) throws RollBackException, NonRollBackException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sd = new MapSqlParameterSource(params);
		namedParameterJdbcTemplate.update(sql, sd , keyHolder);
		return keyHolder;
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ) throws RollBackException, NonRollBackException {
		try {
			return jdbcTemplate.query(sql, rm);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
		
	}
	
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ,Object... params) throws RollBackException, NonRollBackException {
		try {
			return jdbcTemplate.query(sql, rm, params);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
		
		
	}

	@Override
	public <T extends Object> List<T> nativeQuery( String sql, RowMapper<T> rm ,Map<String, Object> params) throws RollBackException, NonRollBackException {
		try {
			
			return namedParameterJdbcTemplate.query(sql, params,rm );
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
	}
	@Override
	public <T extends Object> T nativeQueryOneRow( String sql, RowMapper<T> rm ,Object... params) throws RollBackException, NonRollBackException {
		List<T> resultList = null;
		try {
			resultList = jdbcTemplate.query(sql, rm, params);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
		T result = null;
		if (resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}
	@Override
	public <T extends Object> T nativeQueryOneRow( String sql, RowMapper<T> rm ,Map<String, Object> params) throws RollBackException, NonRollBackException {
		List<T> resultList = null;
		try {
			resultList = namedParameterJdbcTemplate.query(sql, params,rm );
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
		T result = null;
		if (resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}

	@Override
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm) throws RollBackException, NonRollBackException {
		try {
			return jdbcTemplate.queryForObject(sql, rm);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
	}
	
	@Override
	public <T extends Object> T nativeQueryOneRowForObject(String sql, Class<T> requiredType, Object... args)throws RollBackException, NonRollBackException{
		try{
			return jdbcTemplate.queryForObject(sql,requiredType, args);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
	}
	@Override
	public <T extends Object> T nativeQueryOneRowForObject(String sql, Class<T> requiredType,Map<String, Object> args)throws RollBackException, NonRollBackException{
		try{
			return namedParameterJdbcTemplate.queryForObject(sql, args,requiredType);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,ba);
		}
	}
	
	
	@Override
	public <T extends Object>  List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Object... params)throws RollBackException, NonRollBackException {
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM (SELECT (1) FROM "+str[1]+") a" : "Select count(*) from ("+sql+") data";
		
		Long totalRows = nativeQueryOneRowForObject(countQuery,Long.class, params);
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
		
		List<T> resultList = nativeQuery(sb.toString(), rm, params);
		return resultList;
	}
	

	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params) throws RollBackException, NonRollBackException {
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM (SELECT (1) FROM "+str[1]+") a" : "Select count(*) from ("+sql+") data";
		
		Long totalRows = nativeQueryOneRowForObject(countQuery,Long.class,params);
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
		
		List<T> resultList = nativeQuery(sb.toString(), rm, params);
		return resultList;
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm) throws RollBackException, NonRollBackException {
//		String countQuery = "Select count(*) from ("+sql+") data";
		String[] str = sql.toUpperCase().split("FROM");
		String countQuery = str.length == 2 ? "SELECT count(1) FROM (SELECT (1) FROM "+str[1]+") a" : "Select count(*) from ("+sql+") data";
		
		Long totalRows = nativeQueryOneRowForObject(countQuery,Long.class);
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
		List<T> resultList;
		try{
			resultList = nativeQuery(sb.toString(), rm);
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991);
		}
		return resultList;
	}
	@Override
	public <T extends Object> T save(T target) throws RollBackException, NonRollBackException {
		return save(target, true, false,null);
	}
	
	@Override
	public <T extends Object> T save(T target,boolean includeMinusOne) throws RollBackException, NonRollBackException {
		return save(target, includeMinusOne, false,null);
	}
	
	@Override
	public <T extends Object> T save(T target, boolean includeMinusOne, boolean tableLang, String langCode) throws RollBackException, NonRollBackException {
		
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
		if(tableLang)
			sb.append(table.name()+"_LANG");
		else
			sb.append(table.name());
		
//		sb.append(" SET ");
		List<Object> para = new LinkedList<Object>();
//		Method[] arrmet = clazz.getMethods();
		List<String> listColumnName = new LinkedList<String>();
		List<String> listParaName = new LinkedList<String>();
//		List<String> listPkName = new LinkedList<String>();
//		List<Object> listPkId = new LinkedList<Object>();
		Method methodSetId = classMapper.getPropertyId().getMethodSet();
		Method methodGetId = classMapper.getPropertyId().getMethodGet();
		
		if(langCode != null && !langCode.equals("")){
			listColumnName.add("LANG_CODE3");
			listParaName.add("?");
			para.add(langCode); 
		}
		
		for (String  columnName : classMapper.getColumn().keySet()) {
			
			for (Property property : classMapper.getColumn().get(columnName)) {
				Method method = property.getMethodGet();
				try {
					if(property.getColumnType() == ColumnType.column){
						Object value = method.invoke(target);
						if(value != null){
							if(value instanceof Number){
								if(includeMinusOne || ((Number)value).intValue() !=-1){
									listColumnName.add(columnName);
									listParaName.add("?");
//									if(((Number)value).longValue() == 0)
//										value = null;
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
					if(property.getColumnType() == ColumnType.joinColumns){
						Object joinColumnsObject = property.getJoinColumns().getMethodGet().invoke(target);
						if(joinColumnsObject!=null){
							if(property.getEmbeddedId()!=null)
								joinColumnsObject = property.getEmbeddedId().getMethodGet().invoke(joinColumnsObject);
							if(joinColumnsObject == null)
								continue;
							Object value = property.getMethodGet().invoke(joinColumnsObject);
							if(configService.checkNeedleList(columnName) && value != null && ((Number)value).intValue() ==-1){
								continue;
							}
							if(value!=null){
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
//							value = classMapperId.getPropertyId().getMethodGet().invoke(value);
//							listColumnName.add(columnName);
//							listParaName.add("?");
//							para.add(value);
						}
						
					}
					if(property.getColumnType() == ColumnType.joinColumn){
						Object value = method.invoke(target);
						if(value != null){
							Entity entity = method.getReturnType().getAnnotation(Entity.class);
							if(entity!=null){
								ClassMapper classMapperId = JPAUtil.getClassMapper(method.getReturnType());
								value = classMapperId.getPropertyId().getMethodGet().invoke(value);
								if(configService.checkNeedleList(columnName) && value != null && ((Number)value).intValue() ==-1){
									continue;
								}
								if(value!=null ) {
									if(value instanceof Number){
										if(configService.checkClearableList(columnName) && value != null && ((Number)value).intValue() ==-1){
											listColumnName.add(columnName);
											listParaName.add(" (NULL) ");
										}else if(includeMinusOne || ((Number)value).intValue() !=-1){
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
						Object embeddedIdObject = methodGetId.invoke(target);
						if(embeddedIdObject!=null){
							Object valueEmbeddedId = method.invoke(embeddedIdObject);
							if(valueEmbeddedId!=null){
								listColumnName.add(columnName);
								listParaName.add("?");
								para.add(valueEmbeddedId);
							}
						}
					}

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error("save(T, boolean, boolean, String)", e); //$NON-NLS-1$
				}
			
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
		KeyHolder keyHolder;
		try{
			keyHolder = executeNativeSQLGetIdKeyHolder(sb.toString(),para.toArray());
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4994,ba);
		}
		target = idToBean(keyHolder,target,methodSetId, methodGetId);
 		return target;
	}
	
	private <T> T idToBean(KeyHolder keyHolder,T target,Method methodSetId, Method methodGet){
		if(methodSetId !=null&&keyHolder!=null){
			try {
				if(methodSetId.getParameterTypes()!=null&&methodSetId.getParameterTypes().length!=0){
					
					Class<?> type = methodSetId.getParameterTypes()[0];
					if(type == Long.class&& keyHolder.getKey()!=null){
						methodSetId.invoke(target, keyHolder.getKey().longValue());
					}else if(type == Integer.class&& keyHolder.getKey()!=null){
						methodSetId.invoke(target, keyHolder.getKey().intValue());
					}else if(type == Short.class&& keyHolder.getKey()!=null){
						methodSetId.invoke(target, keyHolder.getKey().shortValue());
					}else if(type == Double.class&& keyHolder.getKey()!=null){
						methodSetId.invoke(target, keyHolder.getKey().doubleValue());
					}else if(type.isAnnotationPresent(Embeddable.class)&&keyHolder.getKeys()!=null){
						boolean isSet = false;
						for ( Map<String, Object> keyList : keyHolder.getKeyList()) {
							if(isSet)
								continue;
							for (Entry<String, Object> keyEntry : keyList.entrySet()) {
								if(isSet)
									continue;
								Object key = keyEntry.getValue();
								if (key instanceof Number) {
									Number idValue = (Number) key;
									Class<? extends Object> clazz = target.getClass();
									ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
									Method methodGetId = classMapper.getPropertyId().getMethodGet();
									for (String  columnName : classMapper.getColumn().keySet()) {
										if(isSet)
											continue;
										for (Property property : classMapper.getColumn().get(columnName)) {
											if(isSet)
												continue;
											if(property.getColumnType() == ColumnType.embeddedId){
												Object embeddedIdObject = methodGet.invoke(target);
												if(embeddedIdObject!=null){
													ClassMapper classMapperId = JPAUtil.getClassMapper(methodGetId.getReturnType());
													for (String  colName : classMapperId.getColumn().keySet()) {
														if(isSet)
															continue;
														for (Property prop : classMapperId.getColumn().get(colName)) {
															if(isSet)
																continue;
															Method methodGetEmbeddedId = prop.getMethodGet();
															Method methodSetEmbeddedId = prop.getMethodSet();
															Object value = methodGetEmbeddedId.invoke(embeddedIdObject);
															if(value == null){
																methodSetEmbeddedId.invoke(embeddedIdObject, idValue);
																isSet = true;
																continue;
															}
														}
													}
												}
											}
										}
										
									}
								}
								
							}
						}
						
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("save(T, boolean, boolean, String)", e); //$NON-NLS-1$
			}
		}
		return target;
	}
	@Override
	public <T extends Object> T saveOrUpdate(T t, boolean includeMinusOne) throws RollBackException, NonRollBackException {
		return saveOrUpdate(t, includeMinusOne,false,null,null);
	}
	@Override
	public <T extends Object> T saveOrUpdate(T t) throws RollBackException, NonRollBackException {
		return saveOrUpdate(t, true,false,null,null);
	}
	@Override
	public <T extends Object> T saveOrUpdate(T target, boolean includeMinusOne, boolean tableLang,String code,Long idLang) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(target.getClass());
		Object objectId = null;
		try {
			objectId = classMapper.getPropertyId().getMethodGet().invoke(target);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			
		}
		if(objectId!=null){
			try{
			return update(target, includeMinusOne,tableLang,code,idLang);
			} catch (Exception e){
				logger.error("saveOrUpdate message ", e);
				return save(target, includeMinusOne,tableLang ,code);
			}
			
		}
		else{
			return save(target, includeMinusOne,tableLang ,code);
		}
	}
//	public <T extends Object> T update(T t) throws RollBackException, NonRollBackException {
//		return t;
//	}
	@Override
	public <T extends Object> T update(T target) throws RollBackException, NonRollBackException{
		return update(target, true,false,null,null);
	}
	
	@Override
	public <T extends Object> T update(T target, boolean includeMinusOne) throws RollBackException, NonRollBackException{
		return update(target, includeMinusOne,false,null,null);
	}
	@Override
	public <T extends Object> T update(T target, boolean includeMinusOne, boolean tableLang, String langCode,Long idLang) throws RollBackException, NonRollBackException{
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

//		Method methodGetId = classMapper.getPropertyId().getMethodGet();
//		Method methodSetId = classMapper.getPropertyId().getMethodSet();

		
		sb.append(" UPDATE ");
		if(langCode != null && !langCode.equals("")){
			listColumnName.add("LANG_CODE3");
			listParaName.add("?");
			para.add(langCode); 
		}
		for (String  columnName : classMapper.getColumn().keySet()) {
			for(Property property : classMapper.getColumn().get(columnName)){
				Method method = property.getMethodGet();
				try {
					if(property.getColumnType() == ColumnType.column){
						Object value = method.invoke(target);
						if(value != null){
							if(value instanceof Number){
								if(configService.checkNeedleList(columnName) && value != null && ((Number)value).intValue() ==-1){
									listColumnName.add(columnName);
									listParaName.add(" (NULL) ");
								}else if(includeMinusOne || ((Number)value).intValue() !=-1){
									listColumnName.add(columnName);
									listParaName.add("?");
//									if(((Number)value).intValue() == 0)
//										value = null;
									para.add(value);
								}
							}else if(value instanceof Date){
								if(((Date) value).getTime() == 0L){
									listColumnName.add(columnName);
									listParaName.add(" (NULL) ");
								}else{
									listColumnName.add(columnName);
									listParaName.add("?");
									para.add(value);
								}
							}else{
								if(value.equals("NULL") && "NULL".equals(value)){
									listColumnName.add(columnName);
									listParaName.add(" NULL ");
//									para.add(null);
								}else if(includeMinusOne || (!value.equals("-1") && !"-1".equals(value))){
									listColumnName.add(columnName);
									listParaName.add("?");
									para.add(value);
								} 
							}
						}
					}
					if(property.getColumnType() == ColumnType.joinColumns){
						Object joinColumnsObject = property.getJoinColumns().getMethodGet().invoke(target);
						if(joinColumnsObject!=null){
							if(property.getEmbeddedId()!=null)
								joinColumnsObject = property.getEmbeddedId().getMethodGet().invoke(joinColumnsObject);
							Object value = property.getMethodGet().invoke(joinColumnsObject);
							if(configService.checkNeedleList(columnName) && value != null && ((Number)value).intValue() ==-1){
								continue;
							}
							if(value!=null){
								if(value instanceof Number){
									if(includeMinusOne || ((Number)value).intValue() !=-1){
										listColumnName.add(columnName);
										listParaName.add("?");
										if(((Number)value).intValue() == 0)
											value = null;
										para.add(value);
									}
								}else if(value instanceof Date){
									Date date = new Date();
									date.setTime(((Date) value).getTime());
									if(((Date) value).getTime() == 0L){
										listColumnName.add(columnName);
										listParaName.add(" (NULL) ");
									}else{
										listColumnName.add(columnName);
										listParaName.add("?");
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
//							value = classMapperId.getPropertyId().getMethodGet().invoke(value);
//							listColumnName.add(columnName);
//							listParaName.add("?");
//							para.add(value);
						}
						
					}
					if(property.getColumnType() == ColumnType.joinColumn){
						Object value = method.invoke(target);
						if(value != null){
							Entity entity = method.getReturnType().getAnnotation(Entity.class);
							if(entity!=null){
								ClassMapper classMapperId = JPAUtil.getClassMapper(method.getReturnType());
								
								value = classMapperId.getPropertyId().getMethodGet().invoke(value);
								
								if(configService.checkNeedleList(columnName) && value != null && ((Number)value).intValue() ==-1){
									continue;
								}
								
								if(value!=null ) {
									if(value instanceof Number){
										if(configService.checkClearableList(columnName) && value != null && ((Number)value).intValue() ==-1){
											listColumnName.add(columnName);
											listParaName.add(" (NULL) ");
										}else if(includeMinusOne || ((Number)value).intValue() !=-1){
											listColumnName.add(columnName);
											listParaName.add("?");
											if(((Number)value).intValue() == 0)
												value = null;
											para.add(value);
										}
									}else if(value instanceof Date){
										Date date = new Date();
										date.setTime(((Date) value).getTime());
										if(((Date) value).getTime() == 0L){
											listColumnName.add(columnName);
											listParaName.add(" (NULL) ");
										}else{
											listColumnName.add(columnName);
											listParaName.add("?");
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
						}else{
							if(configService.checkClearableList(columnName)){
								listColumnName.add(columnName);
								listParaName.add(" (NULL) ");
							}
						}
					}
					if(property.getColumnType() == ColumnType.id){
						Object value = method.invoke(target);
						if(value != null){
							if(tableLang){
//								listPkName.add(columnName + "_LANG");
								listPkName.add(columnName);
								listPkNamePara.add(value);
							}else{
								listPkName.add(columnName);
								listPkNamePara.add(value);
							}
						}
					}
					
					if(property.getColumnType() == ColumnType.embeddedId){
						Object embeddedIdObject = property.getEmbeddedId().getMethodGet().invoke(target);
						if(embeddedIdObject!=null){
							Object valueEmbeddedId = method.invoke(embeddedIdObject);
							if(valueEmbeddedId!=null){
								if(tableLang){
									listPkName.add(columnName+"_LANG");
//									listParaName.add("?");
									listPkNamePara.add(valueEmbeddedId);
								}else{
									listPkName.add(columnName);
//									listParaName.add("?");
									listPkNamePara.add(valueEmbeddedId);
								}
								
							}
						}
					}
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					logger.error("update(T, boolean)", e); //$NON-NLS-1$
				}
			}
		}
		if(tableLang)
			sb.append(table.name()+"_LANG");
		else
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
				sb.append( i > 0 ? " AND ":"" );
				sb.append(listPkName.get(i));
				sb.append(" = ? ");
				para.add(listPkNamePara.get(i));
			}
		}
		
		if(langCode != null && !langCode.equals("")){
			sb.append(" AND LANG_CODE3 = ? ");
			para.add(langCode); 
		}
		
		if(listColumnName.size() > 0&&listPkName.size() > 0){
			int row = 0;
			try{
				row = executeNativeSQL(sb.toString(),para.toArray());
			} catch (BadSqlGrammarException ba) {
				throw new RollBackTechnicalException(CommonMessageCode.COM4993,ba);
			}
			if(row == 0){
				throw new RollBackTechnicalException(CommonMessageCode.COM4993," Row Update 0 SQL:" + sb.toString());
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
			throw new RollBackTechnicalException(CommonMessageCode.COM4993,"pk size 0");

		}
		
		return target;
	}
	
//	private Object getData(Method method,Object target) throws RollBackException, NonRollBackException {
//		return null;
//	}
	@Override
	public <T extends Object> T delete(T target) throws RollBackException, NonRollBackException {
		return target;
	}
	@Override
	public <T extends Object> T updateStatusDelete(T target) throws RollBackException, NonRollBackException {
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
		Method methodSetId = classMapper.getPropertyId().getMethodSet();
		Method methodGetId = classMapper.getPropertyId().getMethodGet();
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
				logger.error("updateStatusDelete(T)", e); //$NON-NLS-1$
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
			throw new RollBackTechnicalException(CommonMessageCode.COM4991);
		}
//		Number idNumber =null;
//		try{
//			idNumber = executeNativeSQLGetId(sb.toString(),para.toArray());
//		}catch(Exception e){
//			throw new RollBackTechnicalException(CommonMessageCode.COM4993);
//		}
//		if(methodSetId !=null&&idNumber!=null){
//			try {
//				if(methodSetId.getParameterTypes()!=null&&methodSetId.getParameterTypes().length!=0){
//					
//					Class<?> type = methodSetId.getParameterTypes()[0];
//					if(type == Long.class){
//						methodSetId.invoke(target, idNumber.longValue());
//					}else if(type == Integer.class){
//						methodSetId.invoke(target, idNumber.intValue());
//					}else if(type == Short.class){
//						methodSetId.invoke(target, idNumber.shortValue());
//					}else if(type == Double.class){
//						methodSetId.invoke(target, idNumber.doubleValue());
//					}
//				}
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//				logger.error("updateStatusDelete(T)", e); //$NON-NLS-1$
//			}
//		}
		KeyHolder keyHolder;
		try{
			keyHolder = executeNativeSQLGetIdKeyHolder(sb.toString(),para.toArray());
		} catch (BadSqlGrammarException ba) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4994,ba);
		}
		target = idToBean(keyHolder,target,methodSetId, methodGetId);
		return target;
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1, Object... params) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> T nativeQueryOneRow(String sql, Class<T> class1) throws RollBackException, NonRollBackException {
		return nativeQueryOneRow(sql, JPAUtil.getRm(class1));

	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Object... params) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1) throws RollBackException, NonRollBackException {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1));
	}
	@Override
	public <T> T updateOnlyNotNullBasic(T target) throws RollBackException, NonRollBackException {
		return update(target);
	}
	@Override
	public <T> T get(Serializable target,  Class<T> clazz) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		Property property = classMapper.getPropertyId();
		String sql = "select * from " + classMapper.getTableName() + " where " + property.getColumnName() + " = ?";
		return nativeQueryOneRow(sql , JPAUtil.getRm(clazz), target);
	}
	@Override
	public <T> T get(Serializable target,String lang,  Class<T> clazz) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		Property property = classMapper.getPropertyId();
		String sql = "select * from " + classMapper.getTableName()+"_LANG " + " where " + property.getColumnName() + " = ? and STATUS = 'A' and LANG_CODE3 = ? ";
		return nativeQueryOneRow(sql , JPAUtil.getRm(clazz), target,lang);
	}
	@Override
	public <T> T getByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		Property property = classMapper.getPropertyId();
		String sql = "select * from " + classMapper.getTableName() + " where STATUS = ? and " + property.getColumnName() + " = ? ";
		if(status == null || "".equals(status))
			return null;
		if(pkValue == null || "".equals(pkValue))
			return null;
		return nativeQueryOneRow(sql, clazz, status, pkValue);
	}
	@Override
	public <T> List<T> getListByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		Property property = classMapper.getPropertyId();
		String sql = "select * from " + classMapper.getTableName() + " where STATUS = ? and " + property.getColumnName() + " = ? ";
		if(status == null || "".equals(status))
			return null;
		if(pkValue == null || "".equals(pkValue))
			return null;
		return nativeQuery(sql, clazz, status, pkValue);
	}
//	@Override
	public <T> List<T> findAll(Class<T> clazz) throws RollBackException, NonRollBackException {
		ClassMapper classMapper =JPAUtil.getClassMapper(clazz);
		String sql = "select * from " + classMapper.getTableName();
		return nativeQuery(sql, clazz);
	}
	
	@Override
	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value) throws RollBackException, NonRollBackException {
			return findByColumn(clazz, propertyName, value,null);
	}
	
	@Override
	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackException, NonRollBackException {
		if(pagingBean==null){
			try{
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				String queryString = genQueryStringByExample(clazz, criteriaList, null,null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = nativeQuery(queryString, JPAUtil.getRm(clazz), params);
				return resultList1;
			}catch(RuntimeException e){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
			}
			
		}else{
			try{
				
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				
				pagingBean.setTotalRows(getTotalRowByExample(clazz, criteriaList, null, false));
				
				String qureyString = genQueryStringByExample(clazz, criteriaList, pagingBean,null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 =nativeQuery(qureyString, JPAUtil.getRm(clazz), params);
				
				return resultList1;
				
			}catch (RuntimeException re){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, re);
			}
		}
	}
	
	@Override
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String,Object> columnMap) throws RollBackException, NonRollBackException {
		return findByColumnMap(clazz, columnMap, null);
	}
	
	@Override
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String,Object> columnMap, PagingBean pagingBean) throws RollBackException, NonRollBackException {
		List<Criteria> criteriaList = new LinkedList<Criteria>();
		Iterator<Entry<String, Object>> iterator = columnMap.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<String,Object> column = iterator.next();
			criteriaList.add(new Criteria(column.getKey().toString(), column.getValue()));
		}
		
		if(pagingBean==null){
				String queryString = genQueryStringByExample(clazz, criteriaList, null, null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = nativeQuery(queryString, JPAUtil.getRm(clazz), params);
				return resultList1;
		}else{
				pagingBean.setTotalRows(getTotalRowByExample(clazz, criteriaList, null, false));
				
				String qureyString = genQueryStringByExample(clazz, criteriaList, pagingBean,null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = nativeQuery(qureyString, JPAUtil.getRm(clazz), params);
				
				return resultList1;
				
		}
	}
	
	protected String genQueryStringByExample(Class<?> clazz,List<Criteria> criteriaList,PagingBean pagingBean,String extraWhereClause,boolean like, String langCode3) throws RollBackException, NonRollBackException {
		
		StringBuilder countQueryString = new StringBuilder();
		String tableName = JPAUtil.getClassMapper(clazz).getTableName();
		countQueryString.append("select ");
		countQueryString.append(tableName);
		countQueryString.append(".* ");
		
		if(langCode3 != null && !"".equals(langCode3)){
			
			countQueryString.append(" , IFNULL(");
			countQueryString.append(tableName);
			countQueryString.append("_LANG.NAME, ");
			countQueryString.append(tableName);
			countQueryString.append(".NAME) AS '");
			countQueryString.append(tableName);
			countQueryString.append("|NAME' ");
		}
		
		countQueryString.append(" from ");
		countQueryString.append(tableName);
		
		if(langCode3 != null && !"".equals(langCode3)){
			countQueryString.append(" LEFT JOIN ");
			countQueryString.append(tableName);
			countQueryString.append("_LANG ON ");
			countQueryString.append(tableName);
			countQueryString.append("_LANG.STATUS = :STATUS AND ");
			countQueryString.append(tableName);
			countQueryString.append("_LANG.LANG_CODE3 = :LANG_CODE3 ");
		}
		countQueryString.append(AILIAT);
				
		countQueryString.append(genQueryWhereStringByECriteria(tableName,criteriaList, extraWhereClause, like));

//		if(langCode3 != null && !"".equals(langCode3)){
//			if(criteriaList == null || criteriaList.size() == 0)
//				countQueryString.append(WHERE);
//			else
//				countQueryString.append(" and ");
////			countQueryString.append(CommonDao.ENTITY_MODEL_ALIAS);
//			countQueryString.append(" LANG_CODE3 = '");
//			countQueryString.append(langCode3 + "' ");
//		}
		
//		sb.append(sql);
		if(pagingBean !=null){
			countQueryString.append(genQueryOrderStringByOrders(pagingBean.getOrderList()));
//			
			countQueryString.append(" LIMIT ");
			countQueryString.append(pagingBean.getOffsetBegin());
			countQueryString.append(" , ");
			countQueryString.append(pagingBean.getRowsPerPage());
		}

		return countQueryString.toString();
	}
	
	protected Long getTotalRowByExample(Class<?> clazz,List<Criteria> criteriaList,String extraWhereClause,boolean like) throws RollBackException, NonRollBackException {
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append("select count(*) from ");
		countQueryString.append(JPAUtil.getClassMapper(clazz).getTableName());
		countQueryString.append(AILIAT);
		
		countQueryString.append(genQueryWhereStringByECriteria(JPAUtil.getClassMapper(clazz).getTableName(),criteriaList, extraWhereClause, like));
		Map<String, Object>params = new HashMap<String, Object>();
		if (criteriaList != null && criteriaList.size() > 0) {
			for (Criteria criteria : criteriaList) {
				params.put(criteria.getParam(), criteria.getValue());
			}
		}
		Long totalRows = nativeQueryOneRowForObject(countQueryString.toString(),Long.class, params);
		return totalRows;
	}
	
	protected String genQueryWhereStringByECriteria(String tableName ,List<Criteria> criteriaList,String extraWhereClause,boolean like) throws RollBackException, NonRollBackException {
		StringBuilder queryString = new StringBuilder();
		
		if(criteriaList !=null && criteriaList.size()>0){
			queryString.append(WHERE);
			List<String> criteriaString = new ArrayList<String>();
			for (Criteria criteria : criteriaList) {
				StringBuilder sb = new StringBuilder();
				if(criteria.getValue() instanceof String && like){
					sb.append(UPPER);
					sb.append(tableName);
					sb.append(".");
					sb.append(criteria.getColumn());
					sb.append(LIKE);
					sb.append(criteria.getParam());
				}else{
					sb.append(NON);
					sb.append(tableName);
					sb.append(".");
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
	protected String genQueryOrderStringByOrders(List<Order> orderList) throws RollBackException, NonRollBackException {
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
	private static final String NON = " "; 
	private static final String UPPER = " UPPER( "; 
	private static final String EQU = " = :"; 
	private static final String WHERE = " where "; 
	private static final String FROM = "select * from "; 
	private static final String ORDER = " order by "; 
	private static final String AILIAT = "  "; 
	
	@Override
	public <T> Collection<T> saveOrUpdateAll(final Collection<T> entities) throws RollBackException, NonRollBackException {
		for (T entity : entities) {
			saveOrUpdate(entity);
		}
		return entities;
	}
	
	@Override
	public <T> List<T> findAllEntityOnechild(List<T> list) throws RollBackException, NonRollBackException {

		if(list !=null){
			for (T t : list) {
				try{
					
				
					ClassMapper classMapper = JPAUtil.getClassMapper(t.getClass());
					for (Property property : classMapper.getOneToManyList()) {
						ParameterizedType type = (ParameterizedType)  property.getMethodGet().getGenericReturnType();
						Class<?> classFind = (Class<?>) type.getActualTypeArguments()[0];
						String mapFieldName =  property.getColumnName();//mappedBy="clientDetails"
						Field mapField = classFind.getDeclaredField(mapFieldName);
						Method mapGet = ClassUtil.findGetter(classFind, mapFieldName);
						JoinColumn joinColumn =mapField.getAnnotation(JoinColumn.class);
						if(joinColumn==null)
							joinColumn =mapGet.getAnnotation(JoinColumn.class);
						String nameJoinColumn = joinColumn.name();
						
						List<Property> listProperties = classMapper.getColumn().get(nameJoinColumn);
						Object mapData = null;
						if(listProperties!=null){
							for (Property propertyGetValue : listProperties) {
								mapData = propertyGetValue.getMethodGet().invoke(t);
							}
						}
						List<?> s = findByColumn(classFind, nameJoinColumn, mapData);
						
						property.getMethodSet().invoke(t, s);
					}
					}catch(Exception e){
						
				}
				
			}
		}
//		
		return list;
	}
	@Override
	public <T> List<T> findByColumns(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackException, NonRollBackException{
		if(pagingBean==null){
				String queryString = genQueryStringByExample(clazz, criteriaList, null,null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = nativeQuery(queryString, JPAUtil.getRm(clazz), params);
				return resultList1;
			
		}else{
				pagingBean.setTotalRows(getTotalRowByExample(clazz, criteriaList, null, false));
				
				String qureyString = genQueryStringByExample(clazz, criteriaList, pagingBean,null, false, null);
				Map<String, Object>params = new HashMap<String, Object>();
				if (criteriaList != null && criteriaList.size() > 0) {
					for (Criteria criteria : criteriaList) {
						params.put(criteria.getParam(), criteria.getValue());
					}
				}
				List<T> resultList1 = nativeQuery(qureyString, JPAUtil.getRm(clazz), params);
				return resultList1;
		}
	}
	
	@Override
	public <T> List<T> findByColumns(Class<T> clazz, List<Criteria> criteriaList, String langCode3) throws RollBackException, NonRollBackException{
			String queryString = genQueryStringByExample(clazz, criteriaList, null,null, false, langCode3);
			Map<String, Object>params = new HashMap<String, Object>();
			if (criteriaList != null && criteriaList.size() > 0) {
				for (Criteria criteria : criteriaList) {
					params.put(criteria.getParam(), criteria.getValue());
				}
			}
			if(langCode3!=null&&!"".equals(langCode3)){
				params.put("LANG_CODE3", langCode3);
			}
			List<T> resultList1 = nativeQuery(queryString, JPAUtil.getRm(clazz), params);
			return resultList1;
	}
	
	public <T> T findByColumn(Class<T> clazz, List<Criteria> criteriaList) throws RollBackException, NonRollBackException{
			String queryString = genQueryStringByExample(clazz, criteriaList, null,null, false, null);
			Map<String, Object>params = new HashMap<String, Object>();
			if (criteriaList != null && criteriaList.size() > 0) {
				for (Criteria criteria : criteriaList) {
					params.put(criteria.getParam(), criteria.getValue());
				}
			}
			T result = nativeQueryOneRow(queryString, JPAUtil.getRm(clazz), params);
			return result;
	}
}
