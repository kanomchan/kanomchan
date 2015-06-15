package org.kanomchan.core.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.kanomchan.core.common.bean.ClassMapper;
import org.kanomchan.core.common.bean.Criteria;
import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
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
	public <T extends Object> T save(T target)throws RollBackException ,NonRollBackException{
		return save(target, false, false,null);
	}

	@Override
	public <T extends Object> T update(T target) throws RollBackException ,NonRollBackException{
		return update(target,false,false,null);
	}

	@Override
	public <T extends Object> T delete(T target)throws RollBackException ,NonRollBackException{
		return target;
	}

	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params)throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean, Object... params) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1));
	}

	@Override
	public <T> T update(T entity, String langCode3) throws RollBackException ,NonRollBackException {
		return update(entity, false, true, langCode3);
	}
	@Override
	public <T> T save(T target, String langCode3) throws RollBackException ,NonRollBackException {
		return save(target, false, true, langCode3);
	}
	@Override
	public <T> T delete(T entity, String langCode3) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int update(String jpql) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(String jpql, Object... params) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <T> T findById(Class<T> clazz, Serializable id) throws RollBackException ,NonRollBackException {
		return get(id, clazz);
	}
	@Override
	public <T> T findById(Class<T> clazz, Serializable id,String lang) throws RollBackException ,NonRollBackException {
		return get(id,lang, clazz);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) throws RollBackException ,NonRollBackException {
		return findByColumn(clazz, propertyName, value);
	}
	@Override
	public <T> List<T> findByPropertyWithStatus(Class<T> clazz, String propertyName, Object value, String status) throws RollBackException ,NonRollBackException {
		List<Criteria> criteria = new LinkedList<Criteria>();
		criteria.add(new Criteria(propertyName, value));
		criteria.add(new Criteria("STATUS", status));
		return findByProperty(clazz, criteria,(PagingBean)null);
	}
	@Override
	public <T> List<T> findByPropertyWithStatusAndLang(Class<T> clazz, String propertyName, Object value, String status, String LangCode3) throws RollBackException ,NonRollBackException {
		List<Criteria> criteria = new LinkedList<Criteria>();
		criteria.add(new Criteria(propertyName, value));
		criteria.add(new Criteria("STATUS", status));
		return findByColumns(clazz, criteria, LangCode3);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList) throws RollBackException ,NonRollBackException {
		return findByColumns(clazz, criteriaList, (PagingBean)null);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, String langCode) throws RollBackException ,NonRollBackException {
		return findByColumns(clazz, criteriaList, langCode);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return findByColumns(clazz, criteriaList, pagingBean);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, String extraWhereClause) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, String extraWhereClause) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int executeBatch(String sql) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void flush() throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void refresh(Object entity) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <T extends Object > T saveOrUpdate(T target)throws RollBackException ,NonRollBackException {
		return super.saveOrUpdate(target);
	}
	@Override
	public <T extends Object > T saveOrUpdate(T target, boolean includeMinusOne)throws RollBackException ,NonRollBackException {
		return super.saveOrUpdate(target,includeMinusOne);
	}

	@Override
	public <T> List<T> getListIfNotNull(Class<T> clazz, List<T> list) throws RollBackException ,NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList) throws RollBackException, NonRollBackException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return saveMergeList(clazz, newList, oldList, null);
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList, String SubListColumnName) throws RollBackException, NonRollBackException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		List<T> resultList = new LinkedList<T>();
		ClassMapper classMapper = JPAUtil.getClassMapper(clazz);
		Method methodGet = classMapper.getPropertyId().getMethodGet();
		Method methodSetStatus = classMapper.getColumn().get("STATUS").get(0).getMethodSet();
		
		Method methodGetSubDetail = null;
		Method methodSetSubDetail = null;
		if(SubListColumnName != null){
			methodGetSubDetail = classMapper.getColumn().get(SubListColumnName).get(0).getMethodGet();
			methodSetSubDetail = classMapper.getColumn().get(SubListColumnName).get(0).getMethodSet();
		}
		if(newList != null && newList.size() > 0 && oldList != null && oldList.size() > 0){
			for (T neww : newList) {
				if(neww != null){
					Long idNew = (Long) methodGet.invoke(neww);
					if(idNew != null && idNew > 0){
						for (T old : oldList) {
							Long idOld = (Long) methodGet.invoke(neww);
							if(idOld != null && idOld > 0){
								if(idNew == idOld){
									if(SubListColumnName != null){
										Object subDetail = methodGetSubDetail.invoke(neww);
										Object resultSubDetail = this.saveOrUpdate(subDetail);
										methodSetSubDetail.invoke(neww, resultSubDetail);
									}
									resultList.add((T) this.update(neww));
								}
							}
						}
					}else{
						if(SubListColumnName != null){
							Object subDetail = methodGetSubDetail.invoke(neww);
							Object resultSubDetail = this.save(subDetail);
							methodSetSubDetail.invoke(neww, resultSubDetail);
						}
						resultList.add(this.save(neww));
					}
				}
			}
			for (T old : oldList) {
				if(old != null){
					Long idOld = (Long) methodGet.invoke(old);
					boolean have = false;
					if(idOld != null && idOld > 0){
						for (T neww : newList) {
							Long idNew = (Long) methodGet.invoke(neww);
							if(idNew != null && idNew > 0){
								if(idNew == idOld){
									have = true;
									continue;
								}
							}
						}
					}
					if (have == false) {
						methodSetStatus.invoke(old, "I");
						this.update(old);
					}
				}
			}
		}else if(newList != null && newList.size() > 0){
			for (T neww : newList) {
				if(neww != null){
					if(SubListColumnName != null){
						Object subDetail = methodGetSubDetail.invoke(neww);
						Object resultSubDetail = this.save(subDetail);
						methodSetSubDetail.invoke(neww, resultSubDetail);
					}
					resultList.add(this.save(neww));
				}
			}
		}else if(oldList != null && oldList.size() > 0){
			for (T old : oldList) {
				if(old != null){
					methodSetStatus.invoke(old, "I");
					resultList.add((T) this.update(old));
				}
			}
		}
		return resultList;
	}
}
