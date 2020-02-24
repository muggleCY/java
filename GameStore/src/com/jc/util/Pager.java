package com.jc.util;

import java.util.List;

public class Pager<T> {
	private Integer pageNo;
	private Integer totalPage;
	private List<T> list;

	public void setTotalPage(Integer totalCount, Integer pageSize) {
		totalPage = totalCount / pageSize;
		if (totalCount % pageSize != 0) {
			totalPage++;
		}
	}

	@Override
	public String toString() {
		return "Pager [list=" + list + ", pageNo=" + pageNo + ", totalPage=" + totalPage + "]";
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}

