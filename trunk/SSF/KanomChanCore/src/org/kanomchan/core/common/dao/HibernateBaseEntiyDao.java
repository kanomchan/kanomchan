package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public interface HibernateBaseEntiyDao<T extends EntityBean> extends HibernateBaseDao {

	public T get(Serializable id);

	public List<T> find(String queryString, Object[] values) throws Exception;

	public List<T> find(String queryString, int firstResult, int maxResults,
			Object[] values);

	public T findUnique(String queryString, Object[] values)
			throws NonRollBackException, RollBackException;

	public T findExample(T example);

	public ServiceResult<List<T>> findExampleAndPaging(PagingBean pagingBean, T example);

	public List<T> findByExample(T example);

	public void saveOrUpdate(T entity);

	public void saveOrUpdateAll(Collection<T> entity);

	public List<T> findAll() throws NonRollBackException, RollBackException;

	public void deleteRow(Serializable id) throws RollBackTechnicalException;

	public void delete(Serializable id) throws RollBackTechnicalException;

}
