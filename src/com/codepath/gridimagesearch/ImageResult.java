package com.codepath.gridimagesearch;

import org.json.JSONObject;

public class ImageResult {
	private String url;
	private String thumbUrl;
	
	public ImageResult(JSONObject json){
		try {
			url = json.getString("url");
			thumbUrl = json.getString("tbUrl");
		} catch (Exception e) {
			url = null;
			thumbUrl = null;
		}
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	@Override
	public String toString() {
		return url + " " + thumbUrl;
	}
}
