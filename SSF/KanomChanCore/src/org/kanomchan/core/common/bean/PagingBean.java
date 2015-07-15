package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagingBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9041907739311609317L;

	public class Order implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5279675766966759976L;
		/**
		 * 
		 */
		private String orderBy;
		private ORDER_MODE orderMode;
		
		public Order(String orderBy, ORDER_MODE orderMode) {
			super();
			this.orderBy = orderBy;
			this.orderMode = orderMode;
		}
		
		public String getOrderBy() {
			return orderBy;
		}
		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
		public ORDER_MODE getOrderMode() {
			return ORDER_MODE.DESC==orderMode?ORDER_MODE.DESC:ORDER_MODE.ASC;
		}
		public void setOrderMode(ORDER_MODE orderMode) {
			this.orderMode = orderMode;
		}		

	}
	
	public enum ORDER_MODE implements Serializable {
		DESC("DESC"), ASC("ASC");
		
		private ORDER_MODE(String code) {
			this.code = code;
		}
		private String code;
		@Override
		public String toString() {
			return code;
		}
//		public boolean equalsIgnoreCase(ORDER_MODE mode){
//			return this.code.equalsIgnoreCase(mode.code);
//		}
	}
	
	private static final int DEFAULT_ROWS_PER_PAGE = 10;
	
//	public static final String ORDER_DESC = "DESC";
//	public static final String ORDER_ASC = "ASC";
		
	private static final long DEFAULT_SHOW_PAGE = 10;
	
	private long totalRows;
	private int rowsPerPage;
	private long currentPage;
	
	private String orderBy;		//Name of column
	private ORDER_MODE orderMode;	//ASC or DESC
	private List<Order> orderList;		//For multi order by
	//Order style
	
	public PagingBean() {
		this(DEFAULT_ROWS_PER_PAGE);
	}
	
	public PagingBean(int pageSize) {
		this(pageSize, 1);
	}
	
	public PagingBean(int rowsPerPage, long currentPage) {
		setCurrentPage(currentPage);
		setRowsPerPage(rowsPerPage);
		this.totalRows = 0;
		this.orderMode = ORDER_MODE.ASC;
		orderList = new ArrayList<Order>();
	}
	
	public long getOffsetBegin() {
		return (currentPage-1)*rowsPerPage;
	}
	
	public long getOffsetEnd() {
		return (currentPage*rowsPerPage)-1;
	}
	
	public long getPageCount(){
		long result = totalRows/rowsPerPage + (totalRows%rowsPerPage==0?0:1);
		
		// return at least 1 page.
		// no chance for return 0
		if ( result <= 0 ) {
			result = 1;
		}
		return result;
	}
	
	public long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(long currentPage) {
		if(currentPage <=0) currentPage = 1;
		this.currentPage = currentPage;
	}
	
	public long getPreviousPage() {
		return (currentPage==1)?currentPage:currentPage-1;
	}
	
	public long getNextPage() {
		return (currentPage==getPageCount())?currentPage:currentPage+1;
	}

	
	public List<Long> getDisplayPage() {
		List<Long> displayPageList = new ArrayList<Long>();
		long p = DEFAULT_SHOW_PAGE/2;
		long startDisplayPage = currentPage - p;
		startDisplayPage = (startDisplayPage<1)?1:startDisplayPage;
		long endDisplayPage = startDisplayPage + (DEFAULT_SHOW_PAGE-1);
		long pageCount = getPageCount();
		if(endDisplayPage>pageCount) {
			endDisplayPage = pageCount;
			startDisplayPage = endDisplayPage - (DEFAULT_SHOW_PAGE-1);
			startDisplayPage = (startDisplayPage<1)?1:startDisplayPage;
		}
		for(long i = startDisplayPage; i<=endDisplayPage; i++) {
			displayPageList.add(i);
		}
		return displayPageList;
	}
	
	public long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(long totalRows) {
		// is size under limit set size = 0 
		if(totalRows < 0) {
			totalRows = 0;
		} else {
			this.totalRows = totalRows;
			// adapt currentPage value to match current size 
			if(totalRows < currentPage * rowsPerPage) {
				currentPage = (totalRows/rowsPerPage) + 1;
			}
		}
	}
	
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		if( rowsPerPage <=0 ) rowsPerPage = 1;
		this.rowsPerPage = rowsPerPage;
	}
	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
		this.orderList.clear();
		addOrder( this.orderBy, this.orderMode );
	}

	public ORDER_MODE getOrderMode() {
		return orderMode;
	}
	public void setOrderMode(ORDER_MODE orderMode) {
		this.orderMode = orderMode;
		this.orderList.clear();		
		addOrder( this.orderBy, this.orderMode );		
	}	
	
	public void addOrder( String orderBy, ORDER_MODE orderMode ){
		if( orderBy != null && orderBy.length() > 0 &&
			orderMode != null  ){
			this.orderList.add( new Order( orderBy, orderMode ) );
		}
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
}
