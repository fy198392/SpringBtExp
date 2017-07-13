package com.shinefy.project.util;

import java.io.Serializable;

public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	 /** 总记录数 */ 
    private long total_Count; 
    /**  总页数 **/
    private int total_Page;
    
    private int limitPage=0;
	/** 当前页码 */ 
    private int start=1; 
 
    /** 每页多少 */ 
    private int limit=20; 
 /** 从第几条记录开始 **/
    private int startCount=0;
    
    /**查询条件*/
     private String where;
     
     /*排序*/
     
     private String orderby;
    
     public Page(){
    	 
     }
     public Page(int start,int totalCount){
    	this.start=start;
    	this.total_Count=totalCount;
     }
     
    public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public int getLimitPage() {
		return limitPage;
	}

	public void setLimitPage(int limitPage) {
		this.limitPage = limitPage;
	}

	private int startPage;
    
    public int getStartPage() {
    	if(start<=10){
    		return 1;
    	}else{
    		int p=(int) (start/10);
    		
    		return (start%10)==0?(p-1)*10+1:p*10+1;
    		
    	}
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		
		if(getTotal_Page()<=10){
    		return getTotal_Page();
    	}else{
    		int p=(int) (getTotal_Page()-getStartPage());
    		if(p>=10){
    			return getStartPage()+9;
    		}else{
    			return getTotal_Page();
    		}
    		
    	}
		
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	private int endPage;
	


	public long getTotal_Count() {
		
		return total_Count;
	}

	public void setTotal_Count(long total_Count) {
		
		this.total_Count = total_Count;
	}

	public int getTotal_Page() {
		int p=(int) (total_Count/limit);
		
		total_Page=(total_Count%limit)==0?p:p+1;
		return total_Page;
	}

	public void setTotal_Page(int total_Page) {
		this.total_Page = total_Page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		if(start!=0){
			this.start = start;
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}



	public int getStartCount() {
		
		return (start-1)*limit;
	}

	public void setStartCount(int startCount) {
		
			this.startCount = startCount;
		

	}

	
}
