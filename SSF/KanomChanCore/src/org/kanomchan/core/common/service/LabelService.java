package org.kanomchan.core.common.service;

import java.util.Map;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;

public interface LabelService {

	public Map<String, String> getLabel(String string);

	public void refresh()throws RollBackException ,NonRollBackException;

}
