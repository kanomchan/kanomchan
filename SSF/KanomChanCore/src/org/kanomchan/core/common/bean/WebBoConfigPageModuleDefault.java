package org.kanomchan.core.common.bean;

public class WebBoConfigPageModuleDefault implements WebBoConfigPageModule {

	private Long idWebBoConfigPageModule;
	private String page;
	private String module;
	private String field;
	
	@Override
	public Long getIdWebBoConfigPageModule() {
		return idWebBoConfigPageModule;
	}

	@Override
	public void setIdWebBoConfigPageModule(Long idWebBoConfigPageModule) {
		this.idWebBoConfigPageModule = idWebBoConfigPageModule;
	}

	@Override
	public String getPage() {
		return page;
	}

	@Override
	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String getModule() {
		return module;
	}

	@Override
	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String getField() {
		return field;
	}

	@Override
	public void setField(String field) {
		this.field = field;
	}

}
