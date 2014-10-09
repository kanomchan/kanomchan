package org.kanomchan.formula.bean;


// Generated Oct 7, 2014 3:22:51 PM by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Formula generated by hbm2java
 */
@Entity
@Table(name = "FUM_M_FORMULA")
public class Formula implements java.io.Serializable {

	private Long idFormula;
	private String formulaType;
	private String formulaName;
	private String formula;
	private String out;
	private String unit;
	private String outType;
	private List<FormulaEffect> formulaEffectsForIdFormula = new ArrayList<FormulaEffect>(0);
	private List<FormulaEffect> formulaEffectsForIdFormulaInput = new ArrayList<FormulaEffect>(0);
	
	
	@Transient
	private List<Formula> formulaInput;
//	@Transient
//	private List<Symbol> listSymbol;
	public Formula() {
	}

	public Formula(String formulaType, String formulaName, String formula, String out, String unit, String outType, List<FormulaEffect> formulaEffectsForIdFormula,
			List<FormulaEffect> formulaEffectsForIdFormulaInput) {
		this.formulaType = formulaType;
		this.formulaName = formulaName;
		this.formula = formula;
		this.out = out;
		this.unit = unit;
		this.outType = outType;
		this.formulaEffectsForIdFormula = formulaEffectsForIdFormula;
		this.formulaEffectsForIdFormulaInput = formulaEffectsForIdFormulaInput;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_FORMULA", unique = true, nullable = false)
	public Long getIdFormula() {
		return this.idFormula;
	}

	public void setIdFormula(Long idFormula) {
		this.idFormula = idFormula;
	}

	@Column(name = "FORMULA_TYPE", length = 50)
	public String getFormulaType() {
		return this.formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}

	@Column(name = "FORMULA_NAME")
	public String getFormulaName() {
		return this.formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	@Column(name = "FORMULA")
	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Column(name = "OUT")
	public String getOut() {
		return this.out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	@Column(name = "UNIT")
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "OUT_TYPE")
	public String getOutType() {
		return this.outType;
	}

	public void setOutType(String outType) {
		this.outType = outType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulaByIdFormula")
	public List<FormulaEffect> getFormulaEffectsForIdFormula() {
		return this.formulaEffectsForIdFormula;
	}

	public void setFormulaEffectsForIdFormula(List<FormulaEffect> formulaEffectsForIdFormula) {
		this.formulaEffectsForIdFormula = formulaEffectsForIdFormula;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulaByIdFormulaInput")
	public List<FormulaEffect> getFormulaEffectsForIdFormulaInput() {
		return this.formulaEffectsForIdFormulaInput;
	}

	public void setFormulaEffectsForIdFormulaInput(List<FormulaEffect> formulaEffectsForIdFormulaInput) {
		this.formulaEffectsForIdFormulaInput = formulaEffectsForIdFormulaInput;
	}
	@Transient
	public List<Formula> getFormulaInput() {
		return formulaInput;
	}
	@Transient
	public void setFormulaInput(List<Formula> formulaInput) {
		this.formulaInput = formulaInput;
	}
//	@Transient
//	public String getFormulaDisplay() {
//		return formulaDisplay;
//	}
//	@Transient
//	public void setFormulaDisplay(String formulaDisplay) {
//		this.formulaDisplay = formulaDisplay;
//	}

	
	
}
