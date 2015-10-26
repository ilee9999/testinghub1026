package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.lol.rune.RuneDetailJson;
import com.hkesports.matchticker.model.json.lol.rune.RuneImageJson;
import com.hkesports.matchticker.model.json.lol.rune.RuneJson;
import com.hkesports.matchticker.vo.ApiIF;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "rune_data")
public class RuneData extends BasicModel implements ApiIF {

	private static final long serialVersionUID = 1L;

	private Game game;
	private Long apiId;
	private String description;
	private String name;
	private String imageFullName;
	private Integer imageW;
	private Integer imageH;
	private String version;
	private Boolean isRune;
	private Integer tier;
	private String type;

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

	@Column(name = "is_rune", columnDefinition = "TINYINT(4)", nullable = true)
	public Boolean getIsRune() {
		return isRune;
	}

	public void setIsRune(Boolean isRune) {
		this.isRune = isRune;
	}

	@Column(name = "tier", columnDefinition = "INT(11)", nullable = true)
	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	@Column(name = "type", length = 20, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("apiId", getApiId())
		.append("description", getDescription())
		.append("name", getName())
		.append("imageFullName", getImageFullName())
		.append("imageW", getImageW())
		.append("imageH", getImageH())
		.append("version", getVersion())
		.append("isRune", getIsRune())
		.append("tier", getTier())
		.append("type", getType())
		.build();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RuneJson) {
			RuneJson json = (RuneJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGame().getGameCode(), GameTypeEnum.LOL);
			eb.append(getDescription(), json.getDescription());
			eb.append(getName(), json.getName());
			if(json.getImage() != null) {
				RuneImageJson imageJson = json.getImage();
				eb.append(getImageFullName(), imageJson.getFull());
				eb.append(getImageW(), imageJson.getW());
				eb.append(getImageH(), imageJson.getH());
			}
			if(json.getRune() != null) {
				RuneDetailJson runeDetailJson = json.getRune();
				eb.append(getIsRune(), runeDetailJson.getIsRune());
				eb.append(getTier(), runeDetailJson.getTier());
				eb.append(getType(), runeDetailJson.getType());
			}
			return eb.isEquals();
		}
		return super.equals(obj);
	}
	
	
}
