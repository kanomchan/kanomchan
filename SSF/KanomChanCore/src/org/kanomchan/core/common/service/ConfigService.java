package org.kanomchan.core.common.service;

public interface ConfigService {
	
	public String get(String key);

	public void refreshConfig();

	public void initConfig();
}
