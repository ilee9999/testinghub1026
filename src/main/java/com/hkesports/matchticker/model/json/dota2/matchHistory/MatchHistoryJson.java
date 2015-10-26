package com.hkesports.matchticker.model.json.dota2.matchHistory;

import java.io.Serializable;

public class MatchHistoryJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private MatchHistoryResultJson result;

	public MatchHistoryResultJson getResult() {
		return result;
	}

	public void setResult(MatchHistoryResultJson result) {
		this.result = result;
	}
}
