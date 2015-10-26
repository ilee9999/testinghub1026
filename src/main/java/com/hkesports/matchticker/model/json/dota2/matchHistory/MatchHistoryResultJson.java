package com.hkesports.matchticker.model.json.dota2.matchHistory;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MatchHistoryResultJson implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	@SerializedName("num_results")
	private Integer numResults;
	
	@SerializedName("total_results")
	private Integer totalResults;
	
	@SerializedName("results_remaining")
	private Integer resultsRemaining;
	
	private List<MatchJson> matches;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNumResults() {
		return numResults;
	}

	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}

	public Integer getResultsRemaining() {
		return resultsRemaining;
	}

	public void setResultsRemaining(Integer resultsRemaining) {
		this.resultsRemaining = resultsRemaining;
	}

	public List<MatchJson> getMatches() {
		return matches;
	}

	public void setMatches(List<MatchJson> matches) {
		this.matches = matches;
	}
}
