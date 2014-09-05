package org.kanomchan.core.common.dao;

import java.util.List;

import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.springframework.jdbc.core.RowMapper;

public interface HibernateBaseDao {

	public long count(String queryString, Object[] values);
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean, Object[] params) throws RollBackTechnicalException;
	public void save(EntityBean entity) throws RollBackTechnicalException;
	public void save(Object entity) throws RollBackTechnicalException;
	public void delete(EntityBean entiryBean) throws RollBackTechnicalException;
	public <T extends Object> T update(T entity) throws RollBackTechnicalException;
	public <T extends EntityBean> T update(T entity) throws RollBackTechnicalException;
	public int executeNativeSQL(String sql, Object[] params)throws RollBackTechnicalException;
	public int executeNativeSQL(String sql) throws RollBackTechnicalException;
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, Object[] params)throws RollBackTechnicalException;
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz) throws RollBackTechnicalException;
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean)throws RollBackTechnicalException;
	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> clazz, Object[] params)throws RollBackTechnicalException;
	public <T extends Object> T updateOnlyNotNullBasic(T obj) throws RollBackTechnicalException;
	

}
