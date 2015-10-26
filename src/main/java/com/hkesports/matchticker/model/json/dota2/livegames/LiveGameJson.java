package com.hkesports.matchticker.model.json.dota2.livegames;

public class LiveGameJson {
	private Long league_id;

	public Long getLeague_id() {
		return league_id;
	}

	public void setLeague_id(Long league_id) {
		this.league_id = league_id;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LiveGameJson) {
			return this.getLeague_id().equals(((LiveGameJson) obj).getLeague_id());
		}
		return super.equals(obj);
	}
}
