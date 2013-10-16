package com.codepath.gridimagesearch;

import java.io.Serializable;

public class GoogleSettings implements Serializable{
	
	private static final long serialVersionUID = 1074720227613898597L;
	private String color;
	private String size;
	private String type;
	private String siteFilter;
	
	public GoogleSettings() {
		color = null;
		size = null;
		type = null;
		siteFilter = null;
	}
	
	public String toString(){
		boolean truncateLast = false;
		
		StringBuilder sb = new StringBuilder();
		sb.append("&");
		if(size != null && !size.isEmpty()){
			truncateLast = true;
			
			sb.append("imgsz=");
			sb.append(size.toLowerCase());
			sb.append("&");
		}
		if(type != null && !type.isEmpty()){
			truncateLast = true;

			sb.append("imgtype=");
			sb.append(type.toLowerCase());
			sb.append("&");
		}
		if(color != null && !color.isEmpty()){
			truncateLast = true;

			sb.append("imgcolor=");
			sb.append(color.toLowerCase());
			sb.append("&");
		}
		if(siteFilter != null && !siteFilter.isEmpty()){
			truncateLast = true;

			sb.append("as_sitesearch=");
			sb.append(siteFilter.toLowerCase());
			sb.append("&");
		}
		String s = sb.toString();
		if(truncateLast){
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSiteFilter() {
		return siteFilter;
	}
	public void setSiteFilter(String siteFilter) {
		this.siteFilter = siteFilter;
	}

}
