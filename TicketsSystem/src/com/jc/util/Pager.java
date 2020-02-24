package com.jc.util;

import java.util.List;

public class Pager<T> {
	 private Integer pageNum;//当前页码
	    private List<T> data;//每页显示的数据
	    private Integer totalPage;//总页数   每页显示多少条

	    public void setTotalPage(Integer totalCount,Integer pageSize){
	        totalPage = totalCount/pageSize;
	        if(totalCount%pageSize!=0){
	            totalPage++;
	        }
	    }
	    public Integer getLastPageNum(){
	        return pageNum == 1? 1 : pageNum - 1;
	    }
	    public Integer getNextPageNum(){
	        return pageNum == totalPage? totalPage : pageNum + 1;
	    }
	    public Integer getPageNum() {
	        return pageNum;
	    }
	    public void setPageNum(Integer pageNum) {
	        this.pageNum = pageNum;
	    }
	    public List<T> getData() {
	        return data;
	    }
	    public void setData(List<T> data) {
	        this.data = data;
	    }
	    public Integer getTotalPage() {
	        return totalPage;
	    }
	    public void setTotalPage(Integer totalPage) {
	        this.totalPage = totalPage;
	    }
}
