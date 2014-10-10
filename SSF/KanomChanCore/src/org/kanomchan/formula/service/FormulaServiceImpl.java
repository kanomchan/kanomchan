package org.kanomchan.formula.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.NonRollBackProcessException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.processhandler.ServiceResult;
import org.kanomchan.formula.bean.Formula;
import org.kanomchan.formula.bean.FormulaEffect;
import org.kanomchan.formula.dao.FormulaDao;
import org.kanomchan.formula.util.FormulaUtil;
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.interfaces.IExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class FormulaServiceImpl implements FormulaService {
	
	
	private FormulaDao formulaDao;
	@Autowired
	@Required
	public void setFormulaDao(FormulaDao formulaDao) {
		this.formulaDao = formulaDao;
	}

	@Override
	public ServiceResult<String> calculatorFormula(Long formulaId, Map<Long, Object> dataInput)throws RollBackException,NonRollBackException {
		
		String out = calculatorFormulaLoop(formulaId, dataInput);
		
		return new ServiceResult<String>(out);
	}
	
	

	
	private String calculatorFormulaLoop(Long formulaId, Map<Long, Object> dataInput)throws RollBackException,NonRollBackException{
		Formula formula = formulaDao.getFormula(formulaId);
		if(formula==null)
			throw new NonRollBackProcessException();
		List<FormulaEffect> formulaEffects = formulaDao.getFormulaInput(formulaId);
		StringBuilder formulaBuilder = new StringBuilder();
		EvalUtilities util = new EvalUtilities(false, true);
		if(formulaEffects!=null){
			for (FormulaEffect formulaEffect : formulaEffects) {
				formulaBuilder.append(formulaEffect.getSymbol().getSymbol());
				Formula formulaInput = formulaEffect.getFormulaByIdFormulaInput();
				if(formulaInput==null||FormulaUtil.SYMBOL.equalsIgnoreCase(formulaInput.getFormulaType()))
					continue;
				
				StringBuilder sb = new StringBuilder();
				sb.append(formulaEffect.getSymbol().getSymbol());
				sb.append("=");
				Object obj = dataInput.get(formulaInput.getIdFormula());
				if(obj!=null){
					sb.append(obj.toString());
				}else{
					
					if(FormulaUtil.FORMULA.equalsIgnoreCase(formulaInput.getFormulaType())){
						sb.append(calculatorFormulaLoop(formulaInput.getIdFormula(), dataInput));
					}else{
						sb.append(formulaInput.getOut());
					}
					
				}
				
				util.evaluate(sb.toString());
			}
		}
		
		IExpr out = util.evaluate(formulaBuilder.toString());
		return out.toString();
	}

	@Override
	public ServiceResult<List<Formula>> searchFormula(Formula formula, PagingBean pagingBean) throws RollBackException, NonRollBackException {
		List<Formula>  formulas= formulaDao.search(formula,pagingBean);
		return new ServiceResult<List<Formula>>(formulas, pagingBean);
	}

	@Override
	public ServiceResult<Formula> viewFormula(Long idFormula) throws RollBackException, NonRollBackException {
		Map<Long, Formula> formulaInputMap = new HashMap<Long, Formula>();
		Map<Long, Formula> formulaInputCaseMap = new HashMap<Long, Formula>();
		Formula formula = getInputloop(idFormula,formulaInputMap,formulaInputCaseMap);
		
		List<FormulaEffect> formulaEffects = formulaDao.getFormulaInput(idFormula);
		formula.setFormulaEffectsForIdFormulaInput(formulaEffects);
		List<Formula> formulaInputs = new ArrayList<Formula>(formulaInputMap.values());
		formula.setFormulaInput(formulaInputs);
//		if(formula !=null){
//			List<FormulaEffect> formulaEffects = formulaDao.getFormulaInput(idFormula);
//			if(formulaEffects !=null){
//				for (FormulaEffect formulaEffect : formulaEffects) {
//					Formula formulaInput = formulaEffect.getFormulaByIdFormulaInput();
//					if(formulaInputMap.containsKey(formulaInput.getIdFormula())){
//						continue;
//					}else{
//						if(FORMULA.equalsIgnoreCase(formulaInput.getFormulaType())){
//							
//						}
//					}
//				}
//			}
//		}
			
		
		return new ServiceResult<Formula>(formula);
	}
	
	
	private Formula getInputloop(Long idFormula,Map<Long, Formula> formulaInputMap,Map<Long, Formula> formulaInputCaseMap) throws RollBackException, NonRollBackException{
		Formula formula = formulaDao.getFormula(idFormula);
		if(formula !=null){
			List<FormulaEffect> formulaEffects = formulaDao.getFormulaInput(idFormula);
			if(formulaEffects !=null){
				for (FormulaEffect formulaEffect : formulaEffects) {
					Formula formulaInput = formulaEffect.getFormulaByIdFormulaInput();
					if(formulaInput==null)
						continue;
					if(formulaInputMap.containsKey(formulaInput.getIdFormula())){
						continue;
					}else{
						if(FormulaUtil.FORMULA.equalsIgnoreCase(formulaInput.getFormulaType())){
							if(!formulaInputCaseMap.containsKey(formulaInput.getIdFormula())){
								Formula formulaout = getInputloop(formulaInput.getIdFormula(), formulaInputMap, formulaInputCaseMap);
								formulaInputCaseMap.put(formulaout.getIdFormula(), formulaout);
							}
						}else if(FormulaUtil.INPUT.equalsIgnoreCase(formulaInput.getFormulaType())){
							formulaInputMap.put(formulaInput.getIdFormula(), formulaInput);
						}
					}
				}
			}
		}
		
		return formula;
	}

	
}
