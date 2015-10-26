package com.hkesports.matchticker.model.json.dota2.gameItem;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.google.gson.annotations.SerializedName;
import com.hkesports.matchticker.model.batchJob.ItemData;

public class GameItemJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Integer cost;
	
	@SerializedName("secret_shop")
	private Integer secretShop;
	
	@SerializedName("side_shop")
	private Integer sideShop;
	
	private Integer recipe;
	
	@SerializedName("localized_name")
	private String localizedName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getSecretShop() {
		return secretShop;
	}

	public void setSecretShop(Integer secretShop) {
		this.secretShop = secretShop;
	}

	public Integer getSideShop() {
		return sideShop;
	}

	public void setSideShop(Integer sideShop) {
		this.sideShop = sideShop;
	}

	public Integer getRecipe() {
		return recipe;
	}

	public void setRecipe(Integer recipe) {
		this.recipe = recipe;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof ItemData){
			ItemData item = (ItemData) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getId(), item.getApiId());
			eb.append(getCost(), item.getCost());
			eb.append(getLocalizedName(), item.getName());
			eb.append(getName(), item.getEnName());
			return eb.isEquals();
		}else
			return super.equals(obj);
	}
}
