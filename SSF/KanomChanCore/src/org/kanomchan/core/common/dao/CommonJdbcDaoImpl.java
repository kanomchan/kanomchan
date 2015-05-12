package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.util.JPAUtil;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CommonJdbcDaoImpl extends JdbcCommonDaoImpl implements CommonDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonJdbcDaoImpl.class);

	
	@Override
	public <T extends Object> T save(T target){
		return save(target, false, false,null);
	}

	@Override
	public <T extends Object> T update(T target) throws RollBackTechnicalException{
		return update(target,false,false,null);
	}

	@Override
	public <T extends Object> T delete(T target){
		return target;
	}

	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params){
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) {
		return nativeQuery(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean, Object... params) {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean) {
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1));
	}

	@Override
	public <T> T update(T entity, String langCode3) throws RollBackTechnicalException {
		return update(entity, false, true, langCode3);
	}
	@Override
	public <T> T save(T target, String langCode3) throws RollBackTechnicalException {
		return save(target, false, true, langCode3);
	}
	@Override
	public <T> T delete(T entity, String langCode3) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int update(String jpql) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(String jpql, Object... params) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <T> T findById(Class<T> clazz, Serializable id) throws RollBackTechnicalException {
		return get(id, clazz);
	}
	@Override
	public <T> T findById(Class<T> clazz, Serializable id,String lang) throws RollBackTechnicalException {
		return get(id,lang, clazz);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackTechnicalException {
		return findByColumns(clazz, criteriaList, pagingBean);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, String extraWhereClause) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, String extraWhereClause) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int executeBatch(String sql) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void refresh(Object entity) throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		
	}
}
