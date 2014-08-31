package org.kanomchan.core.common.service;

import java.util.Map;

import org.kanomchan.core.common.bean.*;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.dao.WebBoConfigDao;
import org.kanomchan.core.common.processhandler.ProcessContext;

public class WebBoConfigServiceImpl implements WebBoConfigService {
	
	private WebBoConfigDao webBoConfigDao;
	public Map<Long, Object> WebBoConfigGeography;
	public Map<Long, Object> Region;
	public Map<Long, Object> Country;
	public Map<Long, Object> Zone;
	public Map<Long, Object> Province;
	public Map<Long, Object> City;
	
	public Map<String, Object> Page;
	public Map<String, Object> Field;
	
	
	@Override
	public Map<Object, Object> getWebBoConfigByGeographyPageName(Long idRegion,
			Long idCountry, Long idZone, Long idProvince, Long idCity) {
		return null;
	}

	@Override
	public boolean getWebBoConfigMapList() {

		return false;
	}

	@Override
	public boolean getIsDisplay(Long idRegion, Long idCountry, Long idZone,
		Long idProvince, Long idCity, String page, String field){
		Long idWebBoConfigGeography = webBoConfigDao.getWebBoConfigGeography(idRegion, idCountry, idZone, idProvince, idCity).getIdWebBoConfigGeography();
		Long idWebBoConfigPageModule = webBoConfigDao.getWebBoConfigPageModule(page, field).getIdWebBoConfigPageModule();
		WebBoConfig webBoConfig = webBoConfigDao.getWebBoConfig(idWebBoConfigGeography, idWebBoConfigPageModule);
		return webBoConfig.getIsDisplay().equals("Y");
//		return false;
	}


}
