package org.kanomchan.formula.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.kanomchan.core.common.bean.PagingBean;
import org.kanomchan.core.common.dao.JdbcCommonDaoImpl;
import org.kanomchan.core.common.util.StringUtil;
import org.kanomchan.formula.bean.Formula;
import org.kanomchan.formula.bean.FormulaEffect;
import org.kanomchan.formula.bean.FormulaEffectId;
import org.kanomchan.formula.bean.Symbol;
import org.springframework.jdbc.core.RowMapper;

public class FormulaDaoImpl extends JdbcCommonDaoImpl implements FormulaDao {

	@Override
	public Formula getFormula(Long formulaId) {
		
		return nativeQueryOneRow("SELECT * FROM FUM_M_FORMULA WHERE  ID_FORMULA = ? ", FORMULAMA_MAPPER , formulaId);
	}
	
	private static final RowMapper<Formula> FORMULAMA_MAPPER = new RowMapper<Formula>() {
		
		@Override
		public Formula mapRow(ResultSet rs, int rowNum) throws SQLException {
			Formula formula = new Formula();
			formula.setIdFormula(rs.getLong("ID_FORMULA"));
			formula.setFormula(rs.getString("FORMULA"));
			formula.setFormulaType(rs.getString("FORMULA_TYPE"));
			formula.setFormulaName(rs.getString("FORMULA_NAME"));
			formula.setOut(rs.getString("OUT"));
			formula.setUnit(rs.getString("UNIT"));
			formula.setOutType(rs.getString("OUT_TYPE"));
			return formula;
		}
	};

	@Override
	public List<FormulaEffect> getFormulaInput(Long formulaId) {
		
		
		
		return nativeQuery("SELECT E.`SYMBOL` , E.SEQ , F.*  FROM `FUM_M_FORMULA_EFFECT` E LEFT JOIN `FUM_M_FORMULA` F ON E.`ID_FORMULA` = ? AND F.`ID_FORMULA` = E.`ID_FORMULA_INPUT` WHERE E.`ID_FORMULA` = ? ORDER BY E.ID_FORMULA ,E.SEQ ", FORMULA_EFFECT_MAPPER , formulaId,formulaId);
	}
	
	private static final RowMapper<FormulaEffect> FORMULA_EFFECT_MAPPER = new RowMapper<FormulaEffect>() {
		
		@Override
		public FormulaEffect mapRow(ResultSet rs, int rowNum) throws SQLException {
			FormulaEffect formulaEffect = new FormulaEffect();
			Symbol symbol = new Symbol();
			symbol.setSymbol(rs.getString("SYMBOL"));
			formulaEffect.setSymbol(symbol );
			long s = rs.getLong("ID_FORMULA");
		    if (!rs.wasNull()) {
		    	Formula formula = new Formula();
				formula.setIdFormula(s);
				formula.setFormula(rs.getString("FORMULA"));
				formula.setFormulaType(rs.getString("FORMULA_TYPE"));
				formula.setFormulaName(rs.getString("FORMULA_NAME"));
				formula.setOut(rs.getString("OUT"));
				formula.setUnit(rs.getString("UNIT"));
				formula.setOutType(rs.getString("OUT_TYPE"));
				formulaEffect.setFormulaByIdFormulaInput(formula);
		    }
			
			return formulaEffect;
		}
	};

	@Override
	public List<Formula> search(Formula formula, PagingBean pagingBean) {
		List<Object> para = new LinkedList<Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM FUM_M_FORMULA Where 1=1 ");
		
		
		if(formula!=null){
			if(!StringUtil.isNull(formula.getFormula())){
				sb.append(" AND FORMULA like ? ");
				para.add("%"+formula.getFormula()+"%");
			}
			if(!StringUtil.isNull(formula.getFormulaName())){
				sb.append(" AND FORMULA_NAME like ? ");
				para.add("%"+formula.getFormulaName()+"%");
			}
			if(!StringUtil.isNull(formula.getFormulaType())){
				sb.append(" AND FORMULA_TYPE = ? ");
				para.add(formula.getFormulaType());
			}
		}
		
		return nativeQuery(sb.toString(),pagingBean,FORMULAMA_MAPPER,para.toArray());
	}



}
