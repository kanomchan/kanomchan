package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.springframework.orm.hibernate3.HibernateCallback;


@Transactional
public class HibernateBaseEntiyDaoImpl<T extends EntityBean> extends HibernateBaseDaoImpl implements HibernateBaseEntiyDao<T> {
	
	
	public Logger log = Logger.getLogger(this.getClass());
	
	
	private Class<T> entityClass;
	
	
	@SuppressWarnings("unchecked")
	public HibernateBaseEntiyDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			ParameterizedType parameterizedType = (ParameterizedType) type;
			entityClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		}
		
	}
	
	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if not found.
	 * 
	 * @param id
	 *            the identifier of the persistent instance
	 * @return the persistent instance, or null if not found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(final Serializable id) {
		T o = (T) getHibernateTemplate().get(entityClass, id);
		return o;
	}
	/**
	 * Execute an HQL query, binding a number of values to "?" parameters in the
	 * query string.
	 * 
	 * @param queryString
	 *            a query expressed in Hibernate's query language
	 * @param values
	 *            the values of the parameters
	 * @return a List containing the results of the query execution
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String queryString, Object... values)throws Exception {
		return (List<T>) getHibernateTemplate().find(queryString, values);
	}
	

	/**
	 * Execute an HQL query, binding a number of values to "?" parameters in the
	 * query string.
	 * 
	 * @param queryString
	 *            a query expressed in Hibernate's query language
	 * @param firstResult
	 *            the first result index
	 * @param maxResults
	 *            the maximum result count
	 * @param values
	 *            the values of the parameters
	 * @return List the result list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(final String queryString, final int firstResult,
			final int maxResults, final Object... values) {
		return (List<T>) getHibernateTemplate().executeFind(new HibernateCallback<T>() {
			public T doInHibernate(final Session session) {
				Query query = session.createQuery(queryString);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				if (values != null) {
					for (int i = 0, l = values.length; i < l; i++) {
						query.setParameter(i, values[i]);
					}
				}
				return (T)query.list();
			}
		});
	}
	
	

	/**
	 * Find a single row. If 0 row found, return null; if more than 1 rows
	 * found, throw an IncorrectResultSizeDataAccessException.
	 * 
	 * @param queryString
	 *            a query expressed in Hibernate's query language
	 * @param values
	 *            the values of the parameters
	 * @return a single entity or null if 0 row found. Throws
	 *         IncorrectResultSizeDataAccessException if more than 1 row found.
	 */
	@Override
	public T findUnique( String queryString,  Object... values) throws NonRollBackException, RollBackException {
		if(logger.isDebugEnabled()){
			logger.debug("sql:" + queryString);
			for (Object object : values) {
				logger.debug("object:" + object);
			}
		}
		
		List<T> list;
		try {
			list = this.find(queryString, values);
			int size = list.size();
			T t;
			if (size == 0) {
				t = null;
			} else if (size != 1) {
				throw new IncorrectResultSizeDataAccessException(1, size);
			} else {
				t = list.get(0);
			}
			return t;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, e);
		}
		
		
		
	}
	
	

	/**
	 * Count the rows that meet the conditions.
	 * 
	 * @param queryString
	 *            a query expressed in Hibernate's query language for counting.
	 * @param values
	 *            the values of the parameters
	 * @return the count
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T findExample(T example){
		  T result = null;
		  try {  
		           Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);  
		           result = (T) this.getSession().createCriteria(entityClass).  
		                     add(ex).uniqueResult();  
		     } catch (HibernateException e) {  
		           SessionFactoryUtils.convertHibernateAccessException(e);  
		     }  
		  return result;
		 }
	@Override
	public ServiceResult<List<T>> findAndPagingIgnoreCase(final PagingBean pagingBean ,final T example){
		
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(final Session session) {
//				example.setStatus("A");
				final Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
				final Criteria criteria = session.createCriteria(entityClass).add(Restrictions.disjunction().add(ex));
				List<PagingBean.Order> orderL = pagingBean.getOrderList();
				if(orderL!=null&&orderL.size()>0){
					for (PagingBean.Order order : orderL) {
						if(order.getOrderMode().equals(PagingBean.ORDER_MODE.ASC)){
							criteria.addOrder(Order.asc(order.getOrderBy()) );
						}else{
							criteria.addOrder(Order.desc(order.getOrderBy()) );
						}
					}
				}
				
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.setProjection(Projections.rowCount());
				
				pagingBean.setTotalRows((Long)criteria.uniqueResult());
				
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.setProjection(null);
				criteria.setFirstResult((int) pagingBean.getOffsetBegin());
				criteria.setMaxResults(pagingBean.getRowsPerPage());
				List list = criteria.list();
				return list;
			}
		});
		return new ServiceResult<List<T>>(list,pagingBean);
	}
	@Override
	public ServiceResult<List<T>> findExampleAndPagingIgnoreCase(final PagingBean pagingBean ,final T example){

		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(final Session session) {
				example.setStatus("A");
				final Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);
				final Criteria criteria = session.createCriteria(entityClass).add(Restrictions.disjunction().add(ex));
				List<PagingBean.Order> orderL = pagingBean.getOrderList();
			    if(orderL!=null&&orderL.size()>0){
			    	for (PagingBean.Order order : orderL) {
			    		if(order.getOrderMode().equals(PagingBean.ORDER_MODE.ASC)){
			    			criteria.addOrder(Order.asc(order.getOrderBy()) );
			    		}else{
			    			criteria.addOrder(Order.desc(order.getOrderBy()) );
			    		}
					}
				    
			    	
			    }
			    
			    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			    criteria.setProjection(Projections.rowCount());
			    
			    pagingBean.setTotalRows((Long)criteria.uniqueResult());
			    
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.setProjection(null);
				criteria.setFirstResult((int) pagingBean.getOffsetBegin());
				criteria.setMaxResults(pagingBean.getRowsPerPage());
				List list = criteria.list();
				
			    

				return list;
			}
		});
		  return new ServiceResult<List<T>>(list,pagingBean);
		 }
	
	@Override
	public ServiceResult<List<T>> findExampleAndPaging(final PagingBean pagingBean ,final T example){

		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(final Session session) {
				example.setStatus("A");
				final Example ex = Example.create(example);
				final Criteria criteria = session.createCriteria(entityClass).add(Restrictions.disjunction().add(ex));
				List<PagingBean.Order> orderL = pagingBean.getOrderList();
			    if(orderL!=null&&orderL.size()>0){
			    	for (PagingBean.Order order : orderL) {
			    		if(order.getOrderMode().equals(PagingBean.ORDER_MODE.ASC)){
			    			criteria.addOrder(Order.asc(order.getOrderBy()) );
			    		}else{
			    			criteria.addOrder(Order.desc(order.getOrderBy()) );
			    		}
					}
				    
			    	
			    }
			    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			    criteria.setProjection(Projections.rowCount());
			    
			    pagingBean.setTotalRows((Long)criteria.uniqueResult());
			    
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.setProjection(null);
				criteria.setFirstResult((int) pagingBean.getOffsetBegin());
				criteria.setMaxResults(pagingBean.getRowsPerPage());
				List list = criteria.list();

				return list;
			}
		});
		  return new ServiceResult<List<T>>(list,pagingBean);
		 }
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T example){
//		T result = null;
//		try {  
//			Example ex = Example.create(example).ignoreCase().enableLike(MatchMode.ANYWHERE);  
//			result = (T) this.getSession().createCriteria(entityClass).  
//					add(ex).uniqueResult();  
//		} catch (HibernateException e) {  
//			SessionFactoryUtils.convertHibernateAccessException(e);  
//		}  
		return getHibernateTemplate().findByExample(example);
	}
	@Override
	public void saveOrUpdate(T entity){
		ProcessContext processContext = CurrentThread.getProcessContext();
		if(entity.getCreateDate() == null){
			entity.setCreateDate(new Date());
			entity.setCreateUser(processContext.getUserName());
		}else{
			entity.setUpdateDate(new Date());
			entity.setUpdateUser(processContext.getUserName());
		}
		getHibernateTemplate().saveOrUpdate(entity);
	}
	@Override
	public void saveOrUpdateAll(final Collection<T> entities){
//		getHibernateTemplate().saveOrUpdateAll(entities);
		getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
//				checkWriteOperationAllowed(session);
				for (T entity : entities) {
					ProcessContext processContext = CurrentThread.getProcessContext();
					if(entity.getCreateDate() == null){
						entity.setCreateDate(new Date());
						entity.setCreateUser(processContext.getUserName());
					}else{
						entity.setUpdateDate(new Date());
						entity.setUpdateUser(processContext.getUserName());
					}
					session.saveOrUpdate(entity);
				}
				return null;
			}
		});
	}
	
//	public void save(T entity) throws RollBackTechnicalException{
//		
//	}
	
//	public T update(T entity)throws RollBackTechnicalException{
//		try {
//			T result = getHibernateTemplate().merge(entity);
//			if(logger.isDebugEnabled()) {
//				logger.debug(entity.getClass().getSimpleName()+" merge successful");
//			}
//			return result;
//		} catch (DataIntegrityViolationException re) { 
//			if(logger.isErrorEnabled()) {
//				logger.error("persist failed", re);
//			}
//			throw new RollBackTechnicalException(MessageCode.COM4993, re);
//		} catch (RuntimeException re) {
//			if(logger.isErrorEnabled()) {
//				logger.error("merge failed", re);
//			}
//			throw new RollBackTechnicalException(MessageCode.COM4993, re);
//		}
//	}
	
	@Override
	public List<T> findAll() throws NonRollBackException, RollBackException{
		Criteria criteria = getSession().createCriteria(entityClass);
		if(entityClass.isInstance(EntityBean.class)){
			criteria.add(Restrictions.eq("rowStatus", "A"));
		}
		
		return criteria.list();
	}
	
	
	@Override
	public void deleteRow(Serializable id) throws RollBackTechnicalException{
		getSession().delete(this.get(id));
	}
	
	@Override
	public void delete(Serializable id) throws RollBackTechnicalException{
		try {
			ProcessContext processContext = CurrentThread.getProcessContext();
			//entity = (Object)getSession().get(entity.getClass(), comUserId);
			
			T object = get(id);
			
			if(object instanceof EntityBean){
				EntityBean entiryBean = (EntityBean) object ;
				entiryBean.setStatus("I");
				entiryBean.setCreateUser(processContext.getUserName());
				entiryBean.setCreateDate(new Date());
				getSession().merge(entiryBean);
			}else{
				deleteRow(id);
			}
			
			
			if(logger.isDebugEnabled()) {
				//logger.debug(entity.getClass().getSimpleName() +" persist successful");
			}
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, re);
		}
	}
	

}
