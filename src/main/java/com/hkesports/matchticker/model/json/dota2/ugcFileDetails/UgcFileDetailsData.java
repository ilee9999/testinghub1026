package com.hkesports.matchticker.model.json.dota2.ugcFileDetails;

import java.io.Serializable;

public class UgcFileDetailsData implements Serializable {
	private static final long serialVersionUID = 1L;

	private String filename;
	
	private String url;
	
	private Long size;
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
}
