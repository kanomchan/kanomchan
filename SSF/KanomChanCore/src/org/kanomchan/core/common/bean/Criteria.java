<<<<<<< HEAD
package org.kanomchan.core.common.bean;


public class Criteria {
	
	/**
	 * Class name ( Table ) of Object to use as criteria
	 * required if criteria column is ambiguous ( there are same column name in other table )
	 */
	private String table;
	/**
	 * Field ( Column in Table ) of Object to use as criteria
	 */
	private String column;
	/**
	 * Value to Compare
	 */
	private Object value;
	/**
	 * Name of Parameter in JPQL
	 */
	private String param;

	/**
	 * By Default use column name as param
	 */
	public Criteria(String column, Object value) {
		this( null, column, value, column);
	}
	
	public Criteria(String column, Object value, String param) {
		this( null, column, value,param);
	}
	
	/**
	 * By Default use column name as param
	 */
	public Criteria(String table, String column, Object value ) {
		this( table, column, value, column);
	}
	
	public Criteria(String table, String column, Object value, String param) {
		super();
		this.table = table;
		this.column = column;
		this.value = value;
		this.param = param;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
=======
package org.kanomchan.core.common.bean;


public class Criteria {
	
	/**
	 * Class name ( Table ) of Object to use as criteria
	 * required if criteria column is ambiguous ( there are same column name in other table )
	 */
	private String table;
	/**
	 * Field ( Column in Table ) of Object to use as criteria
	 */
	private String column;
	/**
	 * Value to Compare
	 */
	private Object value;
	/**
	 * Name of Parameter in JPQL
	 */
	private String param;

	/**
	 * By Default use column name as param
	 */
	public Criteria(String column, Object value) {
		this( null, column, value, column);
	}
	
	public Criteria(String column, Object value, String param) {
		this( null, column, value,param);
	}
	
	/**
	 * By Default use column name as param
	 */
	public Criteria(String table, String column, Object value ) {
		this( table, column, value, column);
	}
	
	public Criteria(String table, String column, Object value, String param) {
		super();
		this.table = table;
		this.column = column;
		this.value = value;
		this.param = param;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
>>>>>>> branch 'master' of https://github.com/viatoro/kanomchan.git
