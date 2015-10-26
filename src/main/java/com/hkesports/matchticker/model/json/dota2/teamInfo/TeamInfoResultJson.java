package com.hkesports.matchticker.model.json.dota2.teamInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hkesports.matchticker.vo.Dota2TeamJsonVo;

public class TeamInfoResultJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	
	private List<Map<String, String>> teams;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Map<String, String>> getTeams() {
		return teams;
	}

	public void setTeams(List<Map<String, String>> teams) {
		this.teams = teams;
	}
	
	public List<Dota2TeamJsonVo> bindToVoList(){
		List<Dota2TeamJsonVo> list = new ArrayList<>();
		
		for(Map<String, String> map : this.teams){
			Dota2TeamJsonVo vo = new Dota2TeamJsonVo();
			vo.setTeamId(Long.valueOf(map.get("team_id")));
			vo.setName(map.get("name"));
			vo.setTag(map.get("tag"));
			
			Date date = new Date();
			date.setTime(Long.valueOf(map.get("time_created")));
			vo.setTimeCreated(date);
			
			vo.setRating(map.get("rating"));
			vo.setLogo(Long.valueOf(map.get("logo")));
			vo.setLogoSponsor(Long.valueOf(map.get("logo_sponsor")));
			vo.setCountryCode(map.get("country_code"));
			vo.setUrl(map.get("url"));
			vo.setGamesPlayedWithCurrentRoster(map.get("games_played_with_current_roster"));

			for(String key : map.keySet()){
				if(key.indexOf("league_id") == 0)
					vo.addLeagueId(Long.valueOf(map.get(key)));
				if(key.indexOf("player_") == 0)
					vo.addPlayerId(Long.valueOf(map.get(key)));
			}
			
			list.add(vo);
		}
		
		return list;
	}
	
	/*
	 * teams : [
	 * 	 {
	 * 		"team_id": 1484022,
	 *		"name": "Warlords E-sports",
	 *		"tag": "wL",
	 *		"time_created": 1397152093,
	 *		"rating": "inactive",
	 *		"logo": 3299193636488748644,
	 *		"logo_sponsor": 3299193636488751364,
	 *		"country_code": "br",
	 *		"url": "https://www.facebook.com/WarlordsEsports",
	 *		"games_played_with_current_roster": 0,
	 *		"player_0_account_id": 70809219,
	 *		"player_1_account_id": 86729023,
	 *		"player_2_account_id": 160339576,
	 *		"admin_account_id": 86729023,
	 *		"league_id_0": 228,
	 *		"league_id_1": 1108,
	 *		"league_id_2": 1212,
	 *		"league_id_3": 1232
	 * 
	 *   }
	 * ]
	 */
}
