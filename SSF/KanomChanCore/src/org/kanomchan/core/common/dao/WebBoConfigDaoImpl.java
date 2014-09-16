package org.kanomchan.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.kanomchan.core.common.bean.Label;
import org.kanomchan.core.common.bean.LabelDefault;
import org.kanomchan.core.common.bean.WebBoConfig;
import org.kanomchan.core.common.bean.WebBoConfigDefault;
import org.kanomchan.core.common.bean.WebBoConfigGeography;
import org.kanomchan.core.common.bean.WebBoConfigGeographyDefault;
import org.kanomchan.core.common.bean.WebBoConfigPageModule;
import org.kanomchan.core.common.bean.WebBoConfigPageModuleDefault;
import org.kanomchan.core.common.dao.ConfigDaoImpl.LabelMapper;
import org.springframework.jdbc.core.RowMapper;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

public class WebBoConfigDaoImpl extends JdbcCommonDaoImpl implements WebBoConfigDao {
	
	private static final LabelMapper<Label> LABEL_MAPPER = new LabelMapper<Label>();
	public static final class LabelMapper<T extends Label> implements RowMapper<Label> {
	    public Label mapRow(ResultSet rs, int num)throws SQLException {
	    	LabelDefault label = new LabelDefault(); 
	    	label.setLabel(rs.getString("LABEL"));
	    	label.setLanguage(rs.getString("LANGUAGE"));
	    	label.setPage(rs.getString("PAGE"));
	    	label.setDisplayText(rs.getString("DISPLAY_TEXT"));
	        return label;
	    }
    }
	
	public static final String SQL_QUERY_LABEL = 
			" SELECT LABEL, PAGE, DISPLAY_TEXT, LANGUAGE " +
			" FROM SYS_M_LABEL ";
	
	@Override
	public List<Label> getLabelList() {
		
		List<Label> labelList = nativeQuery(SQL_QUERY_LABEL, LABEL_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		return labelList;
	}

	@Override
	public Map<String, List<Label>> getLabelMap() {
		Map<String, List<Label>> messageMap = new ConcurrentHashMap<String, List<Label>>();
		List<Label> labelList = nativeQuery(SQL_QUERY_LABEL, LABEL_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Label label : labelList) {
			List<Label> labels = messageMap.get(label.getLanguage());
			if(labels==null){
				labels = new ArrayList<Label>();
				
			}
			labels.add(label);
			messageMap.put(label.getLanguage(), labels);
		}
		return messageMap;
	}
	
	@Override
	public Map<String, Map<String, String>> getLabelStrMap() {
		
		Map<String, Map<String, String>> messageStringMap = new ConcurrentHashMap<String, Map<String, String>>();

		Map<String, List<Label>> messageMap   = getLabelMap();
		Map<String, String> labelMapDefault = new ConcurrentHashMap<String, String>();
		for (Label label : messageMap.get(Label.DEFAULT_LANG)) {
			labelMapDefault.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
		}
		Set<String> langSet = messageMap.keySet();
		for (String lang : langSet) {
			if(lang != Label.DEFAULT_LANG){
				Map<String, String> labelMap = new ConcurrentHashMap<String, String>(labelMapDefault);
				for (Label label : messageMap.get(lang)) {
					labelMap.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
				}
				messageStringMap.put(lang, labelMap );
			}
			
		}
		return messageStringMap;
	}
	
	
	
	
	private static final WebBoConfigMapper<WebBoConfig> WEB_BO_CONFIG_MAPPER = new WebBoConfigMapper<WebBoConfig>();
	public static final class WebBoConfigMapper<T extends WebBoConfig> implements RowMapper<WebBoConfig> {
	    public WebBoConfig mapRow(ResultSet rs, int num)throws SQLException {
	    	WebBoConfigDefault webBoConfig = new WebBoConfigDefault(); 
	    	webBoConfig.setIdWebBoConfig(rs.getLong("ID_WEB_BO_CONFIG"));
	    	webBoConfig.setIdWebBoConfigGeography(rs.getLong("ID_WEB_BO_CONFIG_GEOGRAPHY"));
	    	webBoConfig.setIdWebBoConfigPageModule(rs.getLong("ID_WEB_BO_CONFIG_PAGE_MODULE"));
	    	webBoConfig.setIsDisplay(rs.getString("IS_DISPLAY"));
	    	webBoConfig.setIsMandatory(rs.getString("IS_MANDATORY"));
	    	webBoConfig.setIsMatch(rs.getString("IS_MATCH"));
	    	webBoConfig.setIsWeight(rs.getString("IS_WEIGHT"));
	    	webBoConfig.setWeightPercent(rs.getBigDecimal("WEIGHT_PERCENT"));
	    	webBoConfig.setDescription(rs.getString("DESCRIPTION"));
	        return webBoConfig;
	    }
    }
	
	public static final String SQL_QUERY_WEB_BO_CONFIG = 
			" SELECT ID_WEB_BO_CONFIG, ID_WEB_BO_CONFIG_GEOGRAPHY, ID_WEB_BO_CONFIG_PAGE_MODULE IS_DISPLAY, IS_MANDATORY, IS_MATCH, IS_WEIGHT, WEIGHT_PERCENT, DESCRIPTION " +
			" FROM JOB_N_WEB_BO_CONFIG ";
	
	@Override
	public List<WebBoConfig> getWebBoConfigList() {
		List<WebBoConfig> webBoConfigList = nativeQuery(SQL_QUERY_WEB_BO_CONFIG, WEB_BO_CONFIG_MAPPER);
		return webBoConfigList;
	}

	private static final WebBoConfigGeographyMapper<WebBoConfigGeography> WEB_BO_CONFIG_GEOGRAPHY_MAPPER = new WebBoConfigGeographyMapper<WebBoConfigGeography>();
	public static final class WebBoConfigGeographyMapper<T extends WebBoConfigGeography> implements RowMapper<WebBoConfigGeography> {
	    public WebBoConfigGeography mapRow(ResultSet rs, int num)throws SQLException {
	    	WebBoConfigGeographyDefault webBoConfigGeography = new WebBoConfigGeographyDefault(); 
	    	webBoConfigGeography.setIdWebBoConfigGeography(rs.getLong("ID_WEB_BO_CONFIG_GEOGRAPHY"));
	    	webBoConfigGeography.setIdRegion(rs.getLong("ID_REGION"));
	    	webBoConfigGeography.setIdCountry(rs.getLong("ID_COUNTRY"));
	    	webBoConfigGeography.setIdZone(rs.getLong("ID_ZONE"));
	    	webBoConfigGeography.setIdProvince(rs.getLong("ID_PROVINCE"));
	    	webBoConfigGeography.setIdCity(rs.getLong("ID_CITY"));
	        return webBoConfigGeography;
	    }
    }
	
	public static final String SQL_QUERY_WEB_BO_CONFIG_GEOGRAPHY = 
			" SELECT ID_WEB_BO_CONFIG_GEOGRAPHY, ID_REGION, ID_COUNTRY, ID_ZONE, ID_PROVINCE, ID_CITY" +
			" FROM JOB_N_WEB_BO_CONFIG ";
	@Override
	public List<WebBoConfigGeography> getGeographyList() {
		List<WebBoConfigGeography> webBoConfigGeographyList = nativeQuery(SQL_QUERY_WEB_BO_CONFIG_GEOGRAPHY, WEB_BO_CONFIG_GEOGRAPHY_MAPPER);
		return webBoConfigGeographyList;
	}

	private static final WebBoConfigPageModuleMapper<WebBoConfigPageModule> WEB_BO_CONFIG_PAGE_MODULE_MAPPER = new WebBoConfigPageModuleMapper<WebBoConfigPageModule>();
	public static final class WebBoConfigPageModuleMapper<T extends WebBoConfigPageModule> implements RowMapper<WebBoConfigPageModule> {
	    public WebBoConfigPageModule mapRow(ResultSet rs, int num)throws SQLException {
	    	WebBoConfigPageModuleDefault webBoConfig = new WebBoConfigPageModuleDefault(); 
	    	webBoConfig.setIdWebBoConfigPageModule(rs.getLong("ID_WEB_BO_CONFIG_PAGE_MODULE"));
	    	webBoConfig.setPage(rs.getString("PAGE"));
	    	webBoConfig.setModule(rs.getString("MODULE"));
	    	webBoConfig.setField(rs.getString("FIELD"));
	        return webBoConfig;
	    }
    }
	
	public static final String SQL_QUERY_WEB_BO_CONFIG_PAGE_MODULE = 
			" SELECT ID_WEB_BO_CONFIG_PAGE_MODULE, PAGE, MODULE, FIELD " +
			" FROM JOB_N_WEB_BO_CONFIG_PAGE_MODULE ";
	@Override
	public List<WebBoConfigPageModule> getPageModuleList() {
		List<WebBoConfigPageModule> webBoConfigGeographyList = nativeQuery(SQL_QUERY_WEB_BO_CONFIG_PAGE_MODULE, WEB_BO_CONFIG_PAGE_MODULE_MAPPER);
		return webBoConfigGeographyList;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Map<Object, List<Object>> getObjectMap() {
		Map<String, Map<String, String>> messageStringMap = new ConcurrentHashMap<String, Map<String, String>>();

		Map<String, List<Label>> messageMap   = getLabelMap();
		Map<String, String> labelMapDefault = new ConcurrentHashMap<String, String>();
		for (Label label : messageMap.get(Label.DEFAULT_LANG)) {
			labelMapDefault.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
		}
		Set<String> langSet = messageMap.keySet();
		for (String lang : langSet) {
			if(lang != Label.DEFAULT_LANG){
				Map<String, String> labelMap = new ConcurrentHashMap<String, String>(labelMapDefault);
				for (Label label : messageMap.get(lang)) {
					labelMap.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
				}
				messageStringMap.put(lang, labelMap );
			}
			
		}
		//return messageStringMap;
		
		
		return null;
	}

	@Override
	public WebBoConfigGeography getWebBoConfigGeography(Long idRegion, Long idCountry,
			Long idZone, Long idProvince, Long idCity) {
		//WebBoConfigGeography webBoConfigGeography = webbo 
		return null;
	}

	@Override
	public WebBoConfigPageModule getWebBoConfigPageModule(String page, String field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebBoConfig getWebBoConfig(Long idWebBoConfigGeography,
			Long idWebBoConfigPageModule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, Long> getLongMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> objectMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Long, List<WebBoConfigGeography>> getWebBoConfigGeographyMap() {
		Map<Long, List<WebBoConfigGeography>> messageMap = new ConcurrentHashMap<Long, List<WebBoConfigGeography>>();
		List<WebBoConfigGeography> webBoConfigGeographies = nativeQuery(SQL_QUERY_WEB_BO_CONFIG_GEOGRAPHY, WEB_BO_CONFIG_GEOGRAPHY_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (WebBoConfigGeography webBoConfigGeography : webBoConfigGeographies) {
			List<WebBoConfigGeography> webBoConfigGeographieList = messageMap.get(webBoConfigGeography.getIdRegion());
			if(webBoConfigGeographieList==null){
				webBoConfigGeographieList = new ArrayList<WebBoConfigGeography>();
			}
			webBoConfigGeographieList.add(webBoConfigGeography);
			messageMap.put(webBoConfigGeography.getIdRegion(), webBoConfigGeographieList);
		}
		return messageMap;
	}

//	@Override
//	public Map<Object, List<WebBoConfig>> getWebBoConfigMap() {
//		Map<Object, List<WebBoConfig>> webBoConfigMap = new ConcurrentHashMap<Object, List<WebBoConfig>>();
//		List<WebBoConfig> webBoConfigList = nativeQuery(SQL_QUERY_WEB_BO_CONFIG, WEB_BO_CONFIG_MAPPER);
//		
//		for (WebBoConfig webBoConfig : webBoConfigList) {
//			List<WebBoConfig> webBoConfigs = webBoConfigMap.get(webBoConfig.getDescription());//get string
//			if(webBoConfigs==null){
//				webBoConfigs = new ArrayList<WebBoConfig>();
//			}
//			webBoConfigs.add(webBoConfig);
//			webBoConfigMap.put(webBoConfig.getDescription(), webBoConfigs);//get string
//		}
//		return webBoConfigMap;
//	}

//	@Override
//	public Map<Object, Object> getWebBoConfigObjectMap() {
//		Map<Object, Object> webBoConfigStringMap = new ConcurrentHashMap<Object, Object>();
//
//		Map<Object, List<WebBoConfig>> webBoConfigMap = getWebBoConfigMap();
//		//Map<Object, Object> webBoConfigMapDefault = new ConcurrentHashMap<Object, Object>();
//		Map<Object, Object> displayMapDefault = new ConcurrentHashMap<Object, Object>();
//		Map<Object, Object> mandatoryMapDefault = new ConcurrentHashMap<Object, Object>();
//		Map<Object, Object> matchMapDefault = new ConcurrentHashMap<Object, Object>();
//		Map<Object, Object> weightMapDefault = new ConcurrentHashMap<Object, Object>();
//		Map<Object, Object> weightPercentMapDefault = new ConcurrentHashMap<Object, Object>();
//		for (WebBoConfig webBoConfig : webBoConfigMap.get(WebBoConfig.class)) {
//			String key_value = webBoConfig.getIdRegion()+"_"+webBoConfig.getIdCountry()+"_"+webBoConfig.getIdZone()+"_"+webBoConfig.getIdProvince()+"_"+webBoConfig.getIdCity()+"_"+webBoConfig.getPage()+"_"+webBoConfig.getField();
//			displayMapDefault.put(key_value, webBoConfig.getIsDisplay());
//			mandatoryMapDefault.put(key_value, webBoConfig.getIsMandatory());
//			matchMapDefault.put(key_value, webBoConfig.getIsMatch());
//			weightMapDefault.put(key_value, webBoConfig.getIsWeight());
//			weightPercentMapDefault.put(key_value, webBoConfig.getWeightPercent());
//		}
//		
//		return displayMapDefault;
//	}
	
//	@Override
//	public Map<Object, Object> getDisplayObjectMap() {
//		Map<Object, List<WebBoConfig>> webBoConfigMap = getWebBoConfigMap();
//		Map<Object, Object> displayMapDefault = new ConcurrentHashMap<Object, Object>();
//		for (WebBoConfig webBoConfig : webBoConfigMap.get(WebBoConfig.class)) {
//			String key_value = webBoConfig.getIdRegion()+"_"+webBoConfig.getIdCountry()+"_"+webBoConfig.getIdZone()+"_"+webBoConfig.getIdProvince()+"_"+webBoConfig.getIdCity()+"_"+webBoConfig.getPage()+"_"+webBoConfig.getField();
//			displayMapDefault.put(key_value, webBoConfig.getIsDisplay());
//		}		
//		return displayMapDefault;
//	}


	
	
}
