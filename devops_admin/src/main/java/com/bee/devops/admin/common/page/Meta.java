package com.bee.devops.admin.common.page;

import java.io.Serializable;

public class Meta implements Serializable {
	private static final long serialVersionUID = 6530472046648134775L;
	/** 总记录数 */
	private long total;
	/** 第几页 */
	private int pageNum;
	/** 每页记录数 */
	private int pageSize;
	/** 总页数 */
	private int pages;
	/** 当前页的数量 <= pageSize，该属性来自ArrayList的size属性 */
	private int size;

	public Meta() {
		super();
	}

	public Meta(long total, int pageNum, int pageSize, int pages, int size) {
		super();
		this.total = total;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.pages = pages;
		this.size = size;
	}


	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Meta [分页查询起点=" + total + ", 当前页数=" + pageNum + ", 每页数据数=" + pageSize + ", 总页数=" + pages
				+ ", 当前页数量=" + size + "]";
	}
	
}
