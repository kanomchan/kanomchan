package org.kanomchan.core.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.Cache;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.LobHelper;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.TypeHelper;
import org.hibernate.UnknownProfileException;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;
import org.hibernate.type.Type;

public class DelegatingSession implements Session {
	private Session session;
	
	public DelegatingSession(Session session) {
		this.session = session;
	}

	@Override
	public EntityMode getEntityMode() {
		return session.getEntityMode();
	}

	@Override
	public org.hibernate.Session getSession(EntityMode entityMode) {
		return session.getSession(entityMode);
	}

	@Override
	public void flush() throws HibernateException {
		session.flush();
	}

	@Override
	public void setFlushMode(FlushMode flushMode) {
		session.setFlushMode(flushMode);
	}

	@Override
	public FlushMode getFlushMode() {
		return session.getFlushMode();
	}

	@Override
	public void setCacheMode(CacheMode cacheMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CacheMode getCacheMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection connection() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection close() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelQuery() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDirty() throws HibernateException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDefaultReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDefaultReadOnly(boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable getIdentifier(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void evict(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object load(Class theClass, Serializable id, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Class theClass, Serializable id, LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id, LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Class theClass, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(String entityName, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replicate(Object object, ReplicationMode replicationMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replicate(String entityName, Object object, ReplicationMode replicationMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Serializable save(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable save(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object merge(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object merge(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void persist(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object object, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(String entityName, Object object, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LockRequest buildLockRequest(LockOptions lockOptions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object object, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object object, LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LockMode getCurrentLockMode(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction beginTransaction() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(Class persistentClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(Class persistentClass, String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(String entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Criteria createCriteria(String entityName, String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String queryString) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SQLQuery createSQLQuery(String queryString) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createFilter(Object collection, String queryString) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query getNamedQuery(String queryName) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object get(Class clazz, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Class clazz, Serializable id, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Class clazz, Serializable id, LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id, LockMode lockMode) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String entityName, Serializable id, LockOptions lockOptions) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEntityName(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter enableFilter(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filter getEnabledFilter(String filterName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disableFilter(String filterName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SessionStatistics getStatistics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReadOnly(Object entityOrProxy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadOnly(Object entityOrProxy, boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doWork(Work work) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection disconnect() throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reconnect() throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reconnect(Connection connection) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFetchProfileEnabled(String name) throws UnknownProfileException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void enableFetchProfile(String name) throws UnknownProfileException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableFetchProfile(String name) throws UnknownProfileException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeHelper getTypeHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LobHelper getLobHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(String entityName, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object saveOrUpdateCopy(String entityName, Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query, Object value, Type type) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String query, Object[] values, Type[] types) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query, Object value, Type type) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator iterate(String query, Object[] values, Type[] types) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter, Object value, Type type) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection filter(Object collection, String filter, Object[] values, Type[] types) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String query) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String query, Object value, Type type) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String query, Object[] values, Type[] types) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Query createSQLQuery(String sql, String returnAlias, Class returnClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createSQLQuery(String sql, String[] returnAliases, Class[] returnClasses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String entityName, Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String entityName, Object object, Serializable id) throws HibernateException {
		// TODO Auto-generated method stub
		
	}


}
