package org.kanomchan.core.common.bean;

public interface Label {
	public static String DEFAULT_LANG = "ENG";

	public String getLanguage();
	public void setLanguage(String language);
	public String getPage();
	public void setPage(String page);
	public String getLabel();
	public void setLabel(String label);
	public String getDisplayText();
	public void setDisplayText(String displayText);

}
