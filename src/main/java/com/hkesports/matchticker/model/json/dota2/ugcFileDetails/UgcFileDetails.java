package com.hkesports.matchticker.model.json.dota2.ugcFileDetails;

import java.io.Serializable;

public class UgcFileDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private UgcFileDetailsData data;

	public UgcFileDetailsData getData() {
		return data;
	}

	public void setData(UgcFileDetailsData data) {
		this.data = data;
	}
	
}
