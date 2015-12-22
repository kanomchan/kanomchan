package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

public interface JdbcCommonDao {
	
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Map<String, Object> params)throws RollBackException, NonRollBackException;

	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Object... params)throws RollBackException, NonRollBackException;
	
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm)throws RollBackException, NonRollBackException;

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Map<String, Object> params)throws RollBackException, NonRollBackException;

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Object... params) throws RollBackException, NonRollBackException;
	
	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm)throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Object... params)throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params)throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm)throws RollBackException, NonRollBackException;

	public int executeNativeSQL(String sql, Map<String, Object> params)throws RollBackException, NonRollBackException;

	public int executeNativeSQL(String sql, Object... params)throws RollBackException, NonRollBackException;

	public int executeNativeSQL(String sql) throws RollBackException, NonRollBackException;

	public Number executeNativeSQLGetId(String sql, Object... params) throws RollBackException, NonRollBackException;

	public Number executeNativeSQLGetId(String sql, Map<String, Object> params) throws RollBackException, NonRollBackException;

	public Number executeNativeSQLGetId(String sql, EntityBean object) throws RollBackException, NonRollBackException;

//	public <T extends Object> List<T> nativeQueryOneRow(String sql, Class<T> class1, Object[] params) throws RollBackException, NonRollBackException;
	
	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException;

	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1, Object... params) throws RollBackException, NonRollBackException;
	
	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1) throws RollBackException, NonRollBackException;

	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException;

	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, Object... params) throws RollBackException, NonRollBackException;
	
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1) throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Object... params) throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Map<String, Object> params) throws RollBackException, NonRollBackException;
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1) throws RollBackException, NonRollBackException;

	public <T> T updateOnlyNotNullBasic(T target) throws RollBackException, NonRollBackException;
	
	public <T> T get(Serializable target,  Class<T> clazz) throws RollBackException, NonRollBackException;
	
	public <T> T getByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue) throws RollBackException, NonRollBackException;
	
	public <T> List<T> getListByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue) throws RollBackException, NonRollBackException;

	public <T> List<T> findAll(Class<T> class1) throws RollBackException, NonRollBackException;

//	public <T> T save(T target) throws RollBaCckException, NonRollBackException;
	
	public <T> T save(T target) throws RollBackException, NonRollBackException;

	public <T> T save(T target, boolean tableLang,String langCode) throws RollBackException, NonRollBackException;
	
//	public <T> T saveOrUpdate(T t) throws RollBackException, NonRollBackException;

	public <T> T saveOrUpdate(T t) throws RollBackException, NonRollBackException;
	
	public <T> T saveOrUpdate(T target, boolean tableLang,String code,Long idLang) throws RollBackException, NonRollBackException;
	
//	public <T> T update(T target) throws RollBackException, NonRollBackException;

	public <T> T update(T target) throws RollBackException, NonRollBackException;

	public <T> T update(T target, boolean tableLang,String langCode,Long idLang) throws RollBackException, NonRollBackException;

	public <T> Collection<T> saveOrUpdateAll(Collection<T> entities) throws RollBackException, NonRollBackException;

	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value) throws RollBackException, NonRollBackException;

	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value,PagingBean pagingBean) throws RollBackException, NonRollBackException;
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList,PagingBean pagingBean) throws RollBackException, NonRollBackException;
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList, String langCode3) throws RollBackException, NonRollBackException;
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList,PagingBean pagingBean, String langCode3) throws RollBackException,NonRollBackException;
	
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String, Object> columnMap) throws RollBackException, NonRollBackException;
	
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String, Object> columnMap, PagingBean pagingBean) throws RollBackException, NonRollBackException;

	public <T> T updateStatusDelete(T target) throws RollBackException, NonRollBackException;

	public <T> T delete(T target) throws RollBackException, NonRollBackException;

	public <T> List<T> findAllEntityOnechild(List<T> list) throws RollBackException, NonRollBackException;

	public <T> T get(Serializable target, String lang, Class<T> clazz) throws RollBackException, NonRollBackException;

	public <T> T nativeQueryOneRowForObject(String sql, Class<T> requiredType, Object... args)throws RollBackException, NonRollBackException;

	public <T> T nativeQueryOneRowForObject(String sql, Class<T> requiredType,Map<String, Object> args) throws RollBackException, NonRollBackException;

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql,Map<String, Object> params) throws RollBackException,NonRollBackException;

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, EntityBean params)throws RollBackException, NonRollBackException;

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, Object... params)throws RollBackException, NonRollBackException;

	public <T> T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList)throws RollBackException, NonRollBackException;

	public <T>T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList,boolean like) throws RollBackException, NonRollBackException;

	public <T>T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList,String langCode3) throws RollBackException, NonRollBackException;

	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias,PagingBean pagingBean, boolean like, String langCode3)throws RollBackException, NonRollBackException;

	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias)throws RollBackException, NonRollBackException;

	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias,PagingBean pagingBean, boolean like) throws RollBackException,NonRollBackException;

	public Integer getCount(String sql, Object... params) throws RollBackException,NonRollBackException;

	
}
