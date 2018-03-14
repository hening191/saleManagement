package com.hening.sale.common.base;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BasePage implements Serializable{
	
	
	private static int DEFAULT_PAGE_SIZE = 20;
	private int currPage;
	private int start;
	private int limit;
	private int totalRows;


	public int getTotalCount() {
		return totalRows;
	}

	public int getTotalRows() {
		return totalRows;
	}


	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}


	public int getTotalPageCount() {
		if (totalRows % getLimit() == 0)
			return (totalRows / limit);
		else
			return (totalRows / limit + 1);
	}

	public int getLimit() {
		if( limit <= 0 )limit=DEFAULT_PAGE_SIZE;
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrentPageNo() {
		if(getTotalPageCount() > 0 && currPage>getTotalPageCount())currPage=getTotalPageCount();
		return currPage;
	}

	public void setCurrPage(int currPage) {
		if (currPage < 1)currPage = 1;
		this.currPage = currPage;
	}

	public boolean hasNextPage() {
		return getCurrentPageNo() <= getTotalPageCount() - 1L;
	}

	public boolean hasPreviousPage() {
		return getCurrentPageNo() > 1;
	}

	public int getStart() {
		start = (currPage-1)*limit;
		return start;
	}

}
