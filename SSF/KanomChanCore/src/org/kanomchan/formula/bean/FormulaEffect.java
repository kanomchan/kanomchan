package org.kanomchan.formula.bean;


// Generated Oct 7, 2014 3:22:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FormulaEffect generated by hbm2java
 */
@Entity
@Table(name = "FUM_M_FORMULA_EFFECT")
public class FormulaEffect implements java.io.Serializable{

	private FormulaEffectId id;
	private Formula formulaByIdFormula;
	private Symbol symbol;
	private Formula formulaByIdFormulaInput;
	private Integer seq;

	public FormulaEffect() {
	}

	public FormulaEffect(FormulaEffectId id, Formula formulaByIdFormula, Symbol symbol, Formula formulaByIdFormulaInput) {
		this.id = id;
		this.formulaByIdFormula = formulaByIdFormula;
		this.symbol = symbol;
		this.formulaByIdFormulaInput = formulaByIdFormulaInput;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "idFormula", column = @Column(name = "ID_FORMULA", nullable = false)),
			@AttributeOverride(name = "idFormulaInput", column = @Column(name = "ID_FORMULA_INPUT", nullable = false)),
			@AttributeOverride(name = "symbol", column = @Column(name = "SYMBOL", nullable = false, length = 20)) })
	public FormulaEffectId getId() {
		return this.id;
	}

	public void setId(FormulaEffectId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FORMULA", nullable = false, insertable = false, updatable = false)
	public Formula getFormulaByIdFormula() {
		return this.formulaByIdFormula;
	}

	public void setFormulaByIdFormula(Formula formulaByIdFormula) {
		this.formulaByIdFormula = formulaByIdFormula;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYMBOL", nullable = false, insertable = false, updatable = false)
	public Symbol getSymbol() {
		return this.symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_FORMULA_INPUT", nullable = false, insertable = false, updatable = false)
	public Formula getFormulaByIdFormulaInput() {
		return this.formulaByIdFormulaInput;
	}

	public void setFormulaByIdFormulaInput(Formula formulaByIdFormulaInput) {
		this.formulaByIdFormulaInput = formulaByIdFormulaInput;
	}

	@Column(name = "SEQ")
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	
	
}