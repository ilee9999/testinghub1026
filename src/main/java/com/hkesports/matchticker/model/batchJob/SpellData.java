package com.hkesports.matchticker.model.batchJob;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.dota2.ability.Ability;
import com.hkesports.matchticker.model.json.lol.spell.SpellImageJson;
import com.hkesports.matchticker.model.json.lol.spell.SpellJson;
import com.hkesports.matchticker.vo.ApiIF;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "spell_data")
public class SpellData extends BasicModel implements ApiIF {

	private static final long serialVersionUID = 1L;

	private Game game;
	private Long apiId;
	private Hero hero;
	private String description;
	private String name;
	private String enName;
	private Integer summonerLevel;
	private String imageFullName;
	private Integer imageW;
	private Integer imageH;
	private String version;
	private Date expireDate;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "game_id", nullable = true, columnDefinition="BIGINT(20)", referencedColumnName = "id")
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Column(name = "api_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hero_id", columnDefinition="BIGINT(20)", nullable=true)
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	@Column(name = "description", columnDefinition = "TEXT", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "name", length = 255, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "en_name", length = 255, nullable = false)
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name = "summoner_level", columnDefinition = "INT(11)", nullable = true)
	public Integer getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(Integer summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	@Column(name = "image_full_name", length = 100, nullable = true)
	public String getImageFullName() {
		return imageFullName;
	}

	public void setImageFullName(String imageFullName) {
		this.imageFullName = imageFullName;
	}

	@Column(name = "image_w", columnDefinition = "INT(11)", nullable = true)
	public Integer getImageW() {
		return imageW;
	}

	public void setImageW(Integer imageW) {
		this.imageW = imageW;
	}

	@Column(name = "image_h", columnDefinition = "INT(11)", nullable = true)
	public Integer getImageH() {
		return imageH;
	}

	public void setImageH(Integer imageH) {
		this.imageH = imageH;
	}

	@Column(name = "version", length = 20, nullable = true)
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "expire_date", columnDefinition = "datetime", nullable = true)
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("apiId", getApiId())
		.append("description", getDescription())
		.append("name", getName())
		.append("enName", getEnName())
		.append("imageFullName", getImageFullName())
		.append("imageW", getImageW())
		.append("imageH", getImageH())
		.append("version", getVersion())
		.build();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Ability) {
			Ability ability = (Ability) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGame().getGameCode(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), ability.getId());
			eb.append(this.enName, ability.getName());
			eb.append(this.name, ability.getLocalizedName());
			return eb.isEquals();
		} else if(obj instanceof SpellJson) {
			SpellJson json = (SpellJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGame().getGameCode(), GameTypeEnum.LOL);
			eb.append(getApiId(), json.getId());
			eb.append(getDescription(), json.getDescription());
			eb.append(getName(), json.getName());
			eb.append(getEnName(), json.getName());
			eb.append(getSummonerLevel(), json.getSummonerLevel());
			
			if(json.getImage() != null){
				SpellImageJson imageJson = json.getImage();
				eb.append(getImageFullName(), imageJson.getFull());
				eb.append(getImageW(), imageJson.getW());
				eb.append(getImageH(), imageJson.getH());
			}
			return eb.isEquals();
		} else {
			return super.equals(obj);
		}
	}
}
