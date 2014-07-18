package org.kanomchan.core.common.service;

import java.util.Map;

public interface LabelService {

	public Map<String, String> getLabel(String string);

	public void refresh();

}
