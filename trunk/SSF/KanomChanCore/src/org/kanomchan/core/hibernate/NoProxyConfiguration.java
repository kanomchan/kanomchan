package org.kanomchan.core.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class NoProxyConfiguration extends Configuration{

	@Override
	public SessionFactory buildSessionFactory() throws HibernateException {
		
		return super.buildSessionFactory();
	}
}
