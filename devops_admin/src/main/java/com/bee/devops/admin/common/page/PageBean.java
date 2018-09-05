package com.bee.devops.admin.common.page;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
	private static final long serialVersionUID = 8656597559014685635L;
	private Meta meta;
	private List<T> results; // 结果集

	/**
	 * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理， 而出现一些问题。
	 * 
	 * @param list
	 *            page结果
	 *            页码数量
	 */
	public PageBean(List<T> list) {
		meta = new Meta();
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			meta.setPageNum(page.getPageNum()) ;
			meta.setPageSize(page.getPageSize()) ;
			meta.setTotal(page.getTotal()) ;
			meta.setPages(page.getPages()) ;
			this.results=page ;
			meta.setSize(page.size());
		}
	}

	public PageBean(Meta meta, List<T> list) {
		this.meta = meta;
		this.results = list ;
	}
	
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
}
