package org.kanomchan.formula.service;

import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.formula.bean.Formula;

public interface FormulaService {
	
	public ServiceResult<String> calculatorFormula(Long formulaId,Map<Long, Object>  dataInput)throws RollBackException,NonRollBackException;

	public ServiceResult<List<Formula>> searchFormula(Formula formula, PagingBean pagingBean)throws RollBackException,NonRollBackException;

	public ServiceResult<Formula> viewFormula(Long idFormula)throws RollBackException,NonRollBackException;

}
