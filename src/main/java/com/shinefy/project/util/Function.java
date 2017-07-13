package com.shinefy.project.util;

public class Function {
	public static String repalceIt(String s) {
		if (s != null)
			return s.replaceAll("'", "''");
		else
			return "";
	}
	
	
	public static String now() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return sdf.format(new java.util.Date()).toString();
	}
	
	/**
	 * 分页字符串
	 * @param page
	 * @param url
	 * @return
	 */
	 public static String returnSplitPageStr(Page page,String url){
	        StringBuilder sb=new StringBuilder();
	        sb.append("共"+page.getTotal_Count()+"条数据");
	        sb.append("<ul class=\"pagination pull-right\">");
	        if(page.getStart()==1){
	            sb.append("<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>");
	        }else{
	            sb.append("<li><a href=\""+url+"&pagecount="+(page.getStart()-1)+"\">&laquo;</a></li>");
	        }
	        if(page.getStart()>10){
	            sb.append("<li>");
	            sb.append("<a href=\""+url+"&pagecount="+(page.getStart()-10)+"\">...</a>");
	            sb.append("</li>");
	        }
	        for(int i=page.getStartPage();i<=page.getEndPage();i++){
	            if(i==page.getStart()){
	                sb.append("<li class=\"active\">");
	                sb.append("<a href=\""+url+"&pagecount="+i+"\"> "+i+"<span class=\"sr-only\">(current)</span></a>");
	                sb.append("<li>");
	            }else{
	                sb.append("<li>");
	                sb.append("<a href=\""+url+"&pagecount="+i+"\"> "+i+"<span class=\"sr-only\">(current)</span></a>");
	                sb.append("<li>");
	            }
	        }
	        if(page.getTotal_Page()>10&&page.getEndPage()!=page.getTotal_Page()){
	            sb.append("<li>");
	            sb.append("<a href=\""+url+"&pagecount="+(page.getStartPage()+10)+"\">...<span class=\"sr-only\">(current)</span></a>");
	            sb.append("</li>");
	        }
	        if(page.getStart()>=page.getTotal_Page()){
	            sb.append("<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>");
	        }else{
	            sb.append("<li>");
	            sb.append("<a href=\""+url+"&pagecount="+(page.getStart()+1)+"\">&raquo;</a>");
	            sb.append("</li>");
	        }
	        
	        sb.append("</ul>");
	        
	        
	        sb.append("<input  id='_cur_page_count'  type='hidden'  value='"+(page.getStart()-1)+"'   ></input>");
	        return  sb.toString();
	    }
	 
	 public static void main(String[] args){
		 Page page=new Page(1,200);
		 System.out.println(returnSplitPageStr(page,"http://www.baidu.com/"));
	 }
}
