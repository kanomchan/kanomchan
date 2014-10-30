package org.kanomchan.core.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.TypeHelper;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

public class DelegatingSessionFactory implements SessionFactory {
	private SessionFactory sessionFactory;
	public DelegatingSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Reference getReference() throws NamingException {
		return sessionFactory.getReference();
	}

	@Override
	public Session openSession() throws HibernateException {
		return new DelegatingSession(sessionFactory.openSession());
	}

	@Override
	public Session openSession(Interceptor interceptor) throws HibernateException {
		return new DelegatingSession(sessionFactory.openSession(interceptor));
	}

	@Override
	public Session openSession(Connection connection) {
		return new DelegatingSession(sessionFactory.openSession(connection));
	}

	@Override
	public Session openSession(Connection connection, Interceptor interceptor) {
		return new DelegatingSession(sessionFactory.openSession(connection, interceptor));
	}

	@Override
	public Session getCurrentSession() throws HibernateException {
		return new DelegatingSession(sessionFactory.getCurrentSession());
	}

	@Override
	public StatelessSession openStatelessSession() {
		return sessionFactory.openStatelessSession();
	}

	@Override
	public StatelessSession openStatelessSession(Connection connection) {
		return sessionFactory.openStatelessSession(connection);
	}

	@Override
	public ClassMetadata getClassMetadata(Class entityClass) {
		return sessionFactory.getClassMetadata(entityClass);
	}

	@Override
	public ClassMetadata getClassMetadata(String entityName) {
		return sessionFactory.getClassMetadata(entityName);
	}

	@Override
	public CollectionMetadata getCollectionMetadata(String roleName) {
		return sessionFactory.getCollectionMetadata(roleName);
	}

	@Override
	public Map<String, ClassMetadata> getAllClassMetadata() {
		return sessionFactory.getAllClassMetadata();
	}

	@Override
	public Map getAllCollectionMetadata() {
		return sessionFactory.getAllCollectionMetadata();
	}

	@Override
	public Statistics getStatistics() {
		return sessionFactory.getStatistics();
	}

	@Override
	public void close() throws HibernateException {
		sessionFactory.close();
	}

	@Override
	public boolean isClosed() {
		return sessionFactory.isClosed();
	}

	@Override
	public Cache getCache() {
		return sessionFactory.getCache();
	}

	@Override
	public void evict(Class persistentClass) throws HibernateException {
		sessionFactory.evict(persistentClass);
	}

	@Override
	public void evict(Class persistentClass, Serializable id) throws HibernateException {
		sessionFactory.evict(persistentClass, id);
	}

	@Override
	public void evictEntity(String entityName) throws HibernateException {
		sessionFactory.evictEntity(entityName);
	}

	@Override
	public void evictEntity(String entityName, Serializable id) throws HibernateException {
		sessionFactory.evictEntity(entityName, id);
	}

	@Override
	public void evictCollection(String roleName) throws HibernateException {
		sessionFactory.evictCollection(roleName);
	}

	@Override
	public void evictCollection(String roleName, Serializable id) throws HibernateException {
		sessionFactory.evictCollection(roleName, id);
	}

	@Override
	public void evictQueries(String cacheRegion) throws HibernateException {
		sessionFactory.evictQueries(cacheRegion);
	}

	@Override
	public void evictQueries() throws HibernateException {
		sessionFactory.evictQueries();
	}

	@Override
	public Set getDefinedFilterNames() {
		return sessionFactory.getDefinedFilterNames();
	}

	@Override
	public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
		return sessionFactory.getFilterDefinition(filterName);
	}

	@Override
	public boolean containsFetchProfileDefinition(String name) {
		return sessionFactory.containsFetchProfileDefinition(name);
	}

	@Override
	public TypeHelper getTypeHelper() {
		return sessionFactory.getTypeHelper();
	}

}
