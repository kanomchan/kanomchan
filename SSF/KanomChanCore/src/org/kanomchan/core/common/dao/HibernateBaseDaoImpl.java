package org.kanomchan.core.common.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.constant.CommonMessageCode;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.exception.RollBackTechnicalException;
import org.kanomchan.core.common.processhandler.ProcessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



public class HibernateBaseDaoImpl extends HibernateDaoSupport implements HibernateBaseDao {

	public Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}

	@Override
	public long count(final String queryString, final Object... values) {
		return ((Number) getHibernateTemplate().find(queryString, values)
				.get(0)).longValue();
	}
	
	@Override
	public void save(Object entity) throws RollBackTechnicalException{
		try {
			getSession().save( entity ); 
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" persist successful");
			}
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, re);
		}
	}
	@Override
	public void save(EntityBean entity) throws RollBackTechnicalException{
		try {
			ProcessContext processContext = CurrentThread.getProcessContext();
			if(entity.getStatus() == null ||  "".equals(entity.getStatus()))
				entity.setStatus("A");
			entity.setCreateUser(processContext.getUserName());
			entity.setCreateDate(new Date());
			
			getSession().save( entity ); 
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" persist successful");
			}
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, re);
		}
	}
	
	@Override
	public void delete(EntityBean entiryBean) throws RollBackTechnicalException{
		try {
			ProcessContext processContext = CurrentThread.getProcessContext();
			//entity = (Object)getSession().get(entity.getClass(), comUserId);\
			entiryBean.setStatus("I");
			entiryBean.setCreateUser(processContext.getUserName());
			entiryBean.setCreateDate(new Date());
			getSession().merge(entiryBean);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Object> T  update(T entity) throws RollBackTechnicalException {
		try {
			T result = (T) getSession().merge(entity);
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" merge successful");
			}
			return result;
		} catch (DataIntegrityViolationException re) { 
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("merge failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityBean> T  update(T entity) throws RollBackTechnicalException {
		try {
			ProcessContext processContext = CurrentThread.getProcessContext();
			entity.setUpdateUser(processContext.getUserName());
			entity.setUpdateDate(new Date());
			T result = (T) getSession().merge(entity);
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" merge successful");
			}
			return result;
		} catch (DataIntegrityViolationException re) { 
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("merge failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}
	
	@Override
	public int executeNativeSQL(String sql, Object... params) throws RollBackTechnicalException {
		try {
			SQLQuery query = getSession().createSQLQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.executeUpdate();
		} catch (Exception e) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4998, e);
		}
	}
	
	@Override
	public int executeNativeSQL(final String sql) throws RollBackTechnicalException{
		try {
			SQLQuery query = getSession().createSQLQuery(sql);
			return query.executeUpdate();
		} catch (Exception e) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4998, e);
		}
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery(final String sql,Class<T> clazz) throws RollBackTechnicalException {
		
		
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(clazz);
			return query.list();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery(final String sql,Class<T> clazz,Object... params) throws RollBackTechnicalException {
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			
			for(int i=0; i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
			if(clazz == (Long.class) ){
				List<Long> listOut = new LinkedList<Long>();
				List<Number> list = query.list();
				for (Number object : list) {
					listOut.add(object.longValue());
				}
				return (List<T>) listOut;
			}else if(clazz == (Integer.class)){
				List<Integer> listOut = new LinkedList<Integer>();
				List<Number> list = query.list();
				for (Number object : list) {
					listOut.add(object.intValue());
				}
				return (List<T>) listOut;
			}else if(clazz == Double.class ){
				List<Double> listOut = new LinkedList<Double>();
				List<Number> list = query.list();
				for (Number object : list) {
					listOut.add(object.doubleValue());
				}
				return (List<T>) listOut;
			}else if(clazz == String.class ){
				List<String> listOut = new LinkedList<String>();
				List<String> list = query.list();
				for (String object : list) {
					listOut.add(object);
				}
				return (List<T>) listOut;
			}else if(clazz == Float.class ){
				List<Float> listOut = new LinkedList<Float>();
				List<Number> list = query.list();
				for (Number object : list) {
					listOut.add(object.floatValue());
				}
				return (List<T>) listOut;
			}else{
				query.addEntity(clazz);
			}
			return query.list();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}
	
	@Override
	public <T extends Object> List<T> nativeQuery(final String sql,Class<T> clazz,PagingBean pagingBean) throws RollBackTechnicalException {
		
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(clazz);
			String countSql = sql;
			
			String countQuery = "Select count(*) from ("+countSql+") data"; //20091020 by Mix
			SQLQuery countQ = getSession().createSQLQuery(countQuery);
			
			pagingBean.setTotalRows( ((Number)countQ.uniqueResult()).longValue() );
			
			query.setFirstResult((int) pagingBean.getOffsetBegin())
			     .setMaxResults((int) pagingBean.getOffsetEnd());

			return query.list();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}
	@Override
	public <T extends Object> List<T> nativeQuery(final String sql,Class<T> clazz,PagingBean pagingBean, Object... params) throws RollBackTechnicalException {
		
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			query.addEntity(clazz);
			for(int i=0; i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
			String countSql = sql;	
			String countQuery = "Select count(*) from ("+ countSql +") data";
			SQLQuery countQ = getSession().createSQLQuery(countQuery);
			for(int i=0; i<params.length;i++) {
				countQ.setParameter(i, params[i]);
			}
			pagingBean.setTotalRows( ((Number)countQ.uniqueResult()).longValue() );
			query.setFirstResult((int) pagingBean.getOffsetBegin())
			     .setMaxResults((int) pagingBean.getOffsetEnd());
			return query.list();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}
	
	@Override
	public <T extends Object> T nativeQueryOneRow( String sql, Class<T> clazz ,Object... params) throws RollBackTechnicalException {
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			T result = null;
			for(int i=0; i<params.length;i++) {
				query.setParameter(i, params[i]);
			}
			query.addEntity(clazz);
			if (query != null && query.list().size() > 0){
				result = (T) query.list().get(0);
			}
			
			return result;
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
		
		/*List<T> resultList = simpleJdbcTemplate.query(sql, clazz, params);
		T result = null;
		if (resultList != null && resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;*/
//		return simpleJdbcTemplate.queryForObject(sql, rm, params);
	}
}
