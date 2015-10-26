package com.hkesports.matchticker.model.json.dota2.matchDetails;

import java.io.Serializable;

public class MatchDetailsJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private MatchDetailsResultJson result;

	public MatchDetailsResultJson getResult() {
		return result;
	}

	public void setResult(MatchDetailsResultJson result) {
		this.result = result;
	}
}
