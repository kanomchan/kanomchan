package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.kanomchan.core.common.bean.BeanLang;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.TechnicalException;



public interface CommonDao {


//	public static final String ENTITY_MODEL_ALIAS = "model";
	/**
	 * Save method that make an entity instance managed and persistent. <br/>
	 * ( finally : insert into DB ) 
	 * @param entity - Entity object.
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T> T save(T target) throws RollBackException ,NonRollBackException;
	public <T> T save(T target,String langCode3) throws RollBackException ,NonRollBackException;
	
	/**
	 * Update method that merge the state of the given entity into the current persistence context.  <br/>
	 * ( finally : update into DB )
	 * @param entity - Instant of an entity.
	 * @return The instance that the state was merged to .
	 * @throws TechnicalException
	 */
	public <T> T update(T entity) throws RollBackException ,NonRollBackException;
	public <T> T update(T entity,String langCode3,Long idLang) throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute Update with JPQL
	 * @param jpql - JPQL to execute.
	 * @return number of affected rows
	 */
	public int update(final String jpql)throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute Update with JPQL
	 * @param jpql - JPQL to execute.
	 * @param params - Objects to be used as parameter in JPQL.
	 * @return number of affected rows
	 * @throws RollBackException ,NonRollBackException 
	 */
	public int update(final String jpql, Object... params) throws RollBackException ,NonRollBackException;
	
	/**
	 * Delete method that remove the entity instance. 
	 * @param entity - Instant of an entity.
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T> T delete(T entity) throws RollBackException ,NonRollBackException;
	public <T> T delete(T entity,String langCode3) throws RollBackException ,NonRollBackException;
	
	/**
	 * Find by primary key. 
	 * @param clazz - Entity class
	 * @param id - Primary key of entity class
	 * @return The found entity instance or null if the entity does not exist 
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > T findById(Class<T> clazz, Serializable id) throws RollBackException ,NonRollBackException;
	public <T extends Object > T findById(Class<T> class1, Serializable id, String lang) throws RollBackException ,NonRollBackException;
	
	/**
	 * Find by property name.  <br/>
	 * Use with single property only and beware SQL injection in property name.
	 * @param clazz
	 * @param propertyName ( can use property chain with '.' such as 'user.username' )
	 * @param value
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findByProperty( Class<T> clazz, final String propertyName, final Object value ) throws RollBackException ,NonRollBackException;
	public <T extends Object > List<T> findByPropertyWithStatus( Class<T> clazz, final String propertyName, final Object value, String status ) throws RollBackException ,NonRollBackException;
	public <T extends Object > List<T> findByPropertyWithStatusAndLang(Class<T> clazz, final String propertyName, final Object value, final String status, final String langCode) throws RollBackException ,NonRollBackException;
	public <T extends Object > List<T> findByProperty( Class<T> clazz, String propertyName, final Object value, PagingBean pagingBean ) throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute a query based on the given example entity object. <br/>
	 * ( use '=' to compare ) 
	 * @param example
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findByExample(final T example) throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute a query based on the given example entity object.
	 * ( use '=' to compare ) <br/>
	 * Beware SQL injection in extraWhereClause. 
	 * -> Do not concat any input criteria to extraWhereClause <br/>
	 * @param example - Entity object.
	 * @param extraWhereClause
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findByExample(final T example, final String extraWhereClause) throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute a query based on the given example entity object. Control paging by pagingBean.  
	 * ( use '=' to compare )
	 * @param example - Entity object.
	 * @param pagingBean
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findByExample(final T example, PagingBean pagingBean) throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute a query based on the given example entity object. Control paging by pagingBean. <br/>
	 * ( use '=' to compare )
	 * Beware SQL injection in extraWhereClause. <br/>
	 * -> Do not concat any input criteria to extraWhereClause <br/>
	 * @param example
	 * @param pagingBean
	 * @param extraWhereClause
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findByExample(final T example, PagingBean pagingBean, final String extraWhereClause) throws RollBackException ,NonRollBackException;
	
	/**
	 * Same as findByExample() but use 'LIKE' to compare
	 */
	public <T extends Object > List<T> findByExampleLike( final T example ) throws RollBackException ,NonRollBackException;

	public <T extends Object > List<T> findByExampleLike( final T example, final String extraWhereClause ) throws RollBackException ,NonRollBackException;

	public <T extends Object > List<T> findByExampleLike( final T example, PagingBean pagingBean ) throws RollBackException ,NonRollBackException;

	public <T extends Object > List<T> findByExampleLike( final T example, PagingBean pagingBean, final String extraWhereClause ) throws RollBackException ,NonRollBackException;
	
	/**
	 * Get list of all Objects( Records ) of the Class( Table ). <br/>
	 * ( SELECT * FROM TABLE ) 
	 * @param clazz
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findAll(Class<T> clazz) throws RollBackException ,NonRollBackException;
	/**
	 * Get list of all Objects( Records ) of the Class( Table ). Control paging by pagingBean. <br/>
	 * ( SELECT * FROM TABLE ) 
	 * @param clazz
	 * @param pagingBean
	 * @return List of Objects return by the query
	 * @throws RollBackException ,NonRollBackException
	 */
	public <T extends Object > List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackException ,NonRollBackException;
	
	/**
	 * Query with JPQL
	 * @param jpql - JPQL to query.
	 * @return List of Objects return by the query
	 */
	public <T extends Object > List<T> query(String jpql, Class<T> clazz) throws RollBackException ,NonRollBackException ;
	
	/**
	 * Query with JPQL
	 * @param jpql - JPQL to query.
	 * @param params - Objects to be used as parameter in JPQL.
	 * @return List of Objects return by the query
	 */
	public <T extends Object > List<T> query(String jpql, Class<T> clazz, final Object... params)throws RollBackException ,NonRollBackException;
	
	/**
	 * Query with JPQL
	 * @param jpql - JPQL to query.
	 * @param jpqlCount - JPQL to count the total row of 'jpql' result.( use in paging )
	 * @param pagingBean
	 * @param params - Objects to be used as parameter in JPQL.
	 * @return List of Objects return by the query
	 */
	public <T extends Object > List<T> query(String jpql, Class<T> clazz, String jpqlCount, PagingBean pagingBean, Object... params)throws RollBackException ,NonRollBackException;

	/**
	 * Query with JPQL
	 * @param jpql - JPQL to query.
	 * @param list - criteria list append to JPQL )
	 * @return List of Objects return by the query
	 */
//	public <T extends Object > List<T> query(String jpql, final List<Criteria> list)throws RollBackException ,NonRollBackException;
//	
//	public <T extends Object > List<T> query(String jpql, String jpqlCount, PagingBean pagingBean, final List<Criteria> criteriaList) throws RollBackException ,NonRollBackException;
//	
//	public <T extends Object > List<T> queryLike(String jpql, final List<Criteria> criteriaList ) throws RollBackException ,NonRollBackException;
//	
//	public <T extends Object > List<T> queryLike(String jpql, String jpqlCount, final List<Criteria> criteriaList, PagingBean pagingBean ) throws RollBackException ,NonRollBackException;

	/**
	 * Query with JPQL which return only single result <br/>
	 * such as : COUNT, MAX
	 * @param jpql - JPQL to query.
	 * @return Single Result Object
	 */
	public <T extends Object> T querySingleResult(String jpql, Class<T> clazz)throws RollBackException ,NonRollBackException;
	
	public <T extends Object> T querySingleResult(String jpql, Class<T> clazz, Object... params)throws RollBackException ,NonRollBackException;
	
//	public Object querySingleResult(String jpql, final List<Criteria> list)throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute Multi SQL Statements separated by semicolon ';'
	 * @param sql - SQL Statements separated by semicolon ';'
	 * @return 0 if success
	 */
	public int executeBatch(String sql)throws RollBackException ,NonRollBackException;
	
	/**
	 * Execute Native SQL for Update ( INSERT / UPDATE / DELETE ) <br/>
	 * WARNING : Using Native SQL will may cause losing Database vendor migration ability <br/>
	 * avoid specific vendor SQL Command as possible
	 * @param sql - Native SQL 
	 * @return 0 if success
	 */
	public int executeNativeSQL(String sql)throws RollBackException ,NonRollBackException;
	
	public int executeNativeSQL(String sql, Object... params)throws RollBackException ,NonRollBackException;
	
//	/**
//	 * Execute Native SQL for Query <br/>
//	 * Result as List<Object[]> ( So not easy to using the result ) <br/>
//	 *  <br/>
//	 * Example : <br/>
//	 * 	List<Object[]> result = (List<Object[]>) commonDAO.nativeQuery("SELECT * FROM AGI_USER") throws RollBackException ,NonRollBackException ; <br/>
//	 * 	for (Object[] objects : result) {			 <br/>
//	 * 		System.out.println( objects[0] + " : " + objects[1] ) throws RollBackException ,NonRollBackException ; <br/>
//	 * 	} <br/>
//	 *  <br/>
//	 * WARNING : Using Native SQL will may cause losing Database vendor migration ability <br/>
//	 * avoid specific vendor SQL Command as possible
//	 * @param sql - Native SQL 
//	 * @return Result as List<Object[]> ( So not easy to using the result )
//	 * @throws RollBackException ,NonRollBackException 
//	 */
//	public <T extends Object >  List<T> nativeQuery(String sql) throws RollBackException ,NonRollBackException ;
//
//	public <T extends Object >  List<T> nativeQuery(String sql, Object... params) throws RollBackException ,NonRollBackException ;
	
	/**
	 * Execute Native SQL for Query with Mapping Class <br/>
	 * Result as List<Mapped Class> ( Quite easy for using the result )
	 * <p>
	 * Example : <br/>
	 * 	List<CustomUser> result = (List<CustomUser>) commonDAO.nativeQuery("SELECT USER_ID, FIRST_NAME, LAST_NAME FROM AGI_USER",CustomUser.class) throws RollBackException ,NonRollBackException ; <br/>
	 * 	for (CustomUser customUser : result) {			 <br/>
	 * 		System.out.println( customUser.getUserId() + ":" + customUser.getFirstName() + " " + customUser.getLastName() ) throws RollBackException ,NonRollBackException ; <br/>
	 * 	} <br/>
	 * 
	 * WARNING : Using Native SQL will may cause losing Database vendor migration ability <br/>
	 * avoid specific vendor SQL Command as possible
	 * </p>
	 * @param sql - Native SQL 
	 * @return Result as List<Mapped Class> ( Quite easy for using the result )
	 */
	public <T extends Object > List<T> nativeQuery(String sql, Class<T> clazz) throws RollBackException ,NonRollBackException ;
	
	public <T extends Object > List<T> nativeQuery(String sql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException ;
	
	public <T extends Object > List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean) throws RollBackException ,NonRollBackException ;

	public <T extends Object > List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean, Object... params) throws RollBackException ,NonRollBackException ;
	
	/**
	 * Execute Native SQL for Query which return only single result <br/>
	 * such as : COUNT, MAX <br/>
	 * WARNING : Using Native SQL will may cause losing Database vendor migration ability <br/>
	 * avoid specific vendor SQL Command as possible
	 * @param sql - Native SQL 
	 * @return Single Result Object
	 */
	public <T extends Object > Object nativeQuerySingleResult(String sql, Class<T> clazz) throws RollBackException ,NonRollBackException ;
	
	public <T extends Object > Object nativeQuerySingleResult(String sql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException ;
	
	/**
	 * 	Synchronize the persistence context to the underlying database. 
	 */
	public void flush() throws RollBackException ,NonRollBackException ;

	/**
	 * 	Refresh the state of the instance from the database, overwriting changes made to the entity, if any. 
	 */
	public void refresh(Object entity) throws RollBackException ,NonRollBackException ;
	public <T extends Object > List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList) throws RollBackException ,NonRollBackException;
	public <T extends Object > List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, String langCode) throws RollBackException ,NonRollBackException;
	public <T extends Object > List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackException ,NonRollBackException;
	public <T extends Object > T saveOrUpdate(T target) throws RollBackException ,NonRollBackException;
	public <T extends Object > T saveOrUpdate(T target, boolean includeMinusOne) throws RollBackException ,NonRollBackException;
	
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList) throws RollBackException, NonRollBackException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList, String SubListColumnName) throws RollBackException, NonRollBackException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public <T extends Object > BeanLang<T> saveAndLang(BeanLang<T> beanLang, boolean includeMinusOne) throws RollBackException, NonRollBackException, IllegalAccessException;

	
}
