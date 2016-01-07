package com.common.util.page;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {
	  private static final long serialVersionUID = -379070723132246175L;
	  private int totalRows;
//	  public static final int limit = Integer.parseInt(
//	    PropertiesFactory.getPropertiesHelper("model").getValue(
//	    "paging.number.per", "15"));
	  
	  public static final int limit =10;

	  private int pageSize = limit;
	  private int currentPage;
	  private int totalPages = 0;
	  private int startRow;
	  private List list;

	  public Pager()
	  {
	  }

	  public Pager(int _totalRows)
	  {
	    this.totalRows = _totalRows;
	    this.totalPages = (this.totalRows / this.pageSize);
	    int mod = this.totalRows % this.pageSize;
	    if (mod > 0) {
	      this.totalPages += 1;
	    }
	    this.currentPage = 1;
	    this.startRow = 0;
	  }

	  public void first()
	  {
	    this.currentPage = 1;
	    this.startRow = 0;
	  }

	  public void previous()
	  {
	    if (this.currentPage == 1) {
	      return;
	    }
	    this.currentPage -= 1;
	    this.startRow = ((this.currentPage - 1) * this.pageSize);
	  }

	  public void next()
	  {
	    if (this.currentPage < this.totalPages) {
	      this.currentPage += 1;
	    }
	    this.startRow = ((this.currentPage - 1) * this.pageSize);
	  }

	  public void last()
	  {
	    this.currentPage = this.totalPages;
	    this.startRow = ((this.currentPage - 1) * this.pageSize);
	  }

	  public void refresh(int _currentPage)
	  {
	    this.currentPage = _currentPage;
	    if (this.currentPage == 0) {
	      this.currentPage = 1;
	    }
	    this.startRow = ((this.currentPage - 1) * this.pageSize);
	    if ((this.totalPages > 0) && (this.currentPage > this.totalPages))
	      last();
	  }

	  public int getStartRow()
	  {
	    return this.startRow;
	  }

	  public int getTotalPages() {
	    return this.totalPages;
	  }

	  public int getCurrentPage() {
	    return this.currentPage;
	  }

	  public int getPageSize() {
	    return this.pageSize;
	  }

	  public void setTotalRows(int totalRows) {
	    this.totalRows = totalRows;
	    this.totalPages = (totalRows / this.pageSize);
	    int mod = totalRows % this.pageSize;
	    if (mod > 0)
	      this.totalPages += 1;
	  }

	  public void setStartRow(int startRow)
	  {
	    this.startRow = startRow;
	  }

	  public void setTotalPages(int totalPages) {
	    this.totalPages = totalPages;
	  }

	  public void setCurrentPage(int currentPage) {
	    this.currentPage = currentPage;
	  }

	  public void setPageSize(int pageSize) {
	    this.pageSize = pageSize;
	  }

	  public int getTotalRows() {
	    return this.totalRows;
	  }

	  public List getList()
	  {
	    return this.list;
	  }

	  public void setList(List list)
	  {
	    this.list = list;
	  }}
