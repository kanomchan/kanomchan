package org.kanomchan.formula.dao;

import java.util.List;

import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.formula.bean.Formula;
import org.kanomchan.formula.bean.FormulaEffect;

public interface FormulaDao {

	
	public Formula getFormula(Long formulaId);
	
	public List<FormulaEffect> getFormulaInput(Long formulaId);

	public List<Formula> search(Formula formula, PagingBean pagingBean); 
}
