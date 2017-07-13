package org.kanomchan.core.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class NoProxyConfiguration extends AnnotationConfiguration{

	@Override
	public SessionFactory buildSessionFactory() throws HibernateException {
		
		return new DelegatingSessionFactory(super.buildSessionFactory());
	}
}
