package org.kanomchan.core.common.service;

import javax.persistence.RollbackException;

import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;

public class DisplayFieldImpl implements DisplayField {

	@Override
	public ServiceResult<DisplayField> getDisplayFieldByMany(Long idZone,
			Long idCountry, Long idProvince, String page, String field)
			throws RollbackException, NonRollBackException {
		return getDisplayFieldByMany(idZone, idCountry, idProvince, page, field);
	}



}
