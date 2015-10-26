package com.hkesports.matchticker.model.json.factory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author manboyu
 *
 * @param <T>
 */
public class JsonFactory<T> {

	private Gson gson;
	private T data;
	
	public JsonFactory() {
	}
	
	public JsonFactory(String jsonStr){
		this.gson = new GsonBuilder().create();
		this.checkJsonStr(jsonStr);
	}
	
	public JsonFactory(String jsonStr, String dateformat){
		this.gson = new GsonBuilder().setDateFormat(dateformat).create();
		this.checkJsonStr(jsonStr);
	}
	
	public JsonFactory(String jsonStr, Type type) {
		this.gson = new GsonBuilder().create();
		this.checkJsonStr(jsonStr, type);
	}
	
	public JsonFactory(String jsonStr, String dateformat, Type type) {
		this.gson = new GsonBuilder().setDateFormat(dateformat).create();
		this.checkJsonStr(jsonStr, type);
	}
	
	public JsonFactory(String jsonStr,CustomDateDeserializer customDeserial, Type type){
		this.gson = new GsonBuilder().registerTypeAdapter(Date.class, customDeserial).create();
		this.checkJsonStr(jsonStr, type);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isEmpty(){
		return this.data == null;
	}
	
	private void checkJsonStr(String jsonStr){
		if(StringUtils.isBlank(jsonStr)){
			this.data = null;
		} else {
			this.data = gson.fromJson(jsonStr, new TypeToken<T>() {}.getType());
		}
	}
	
	private void checkJsonStr(String jsonStr, Type type){
		if(StringUtils.isBlank(jsonStr)){
			this.data = null;
		} else {
			this.data = gson.fromJson(jsonStr, type);
		}
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("data", getData())
		.build();
	}
	
	
	public static <E> E fromJson(String jsonStr, Type type) {
		if(StringUtils.isBlank(jsonStr)) {
			return null;
		}
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonStr, type);
	}
	
	public static <E> E fromJson(String jsonStr, String dateformat, Type type) {
		if(StringUtils.isBlank(jsonStr)) {
			return null;
		}
		Gson gson = new GsonBuilder().setDateFormat(dateformat).create();
		return gson.fromJson(jsonStr, type);
	}
	
	public static <E> E fromJson(String jsonStr, CustomDateDeserializer customDeserial, Type type) {
		if(StringUtils.isBlank(jsonStr)) {
			return null;
		}
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, customDeserial).create();
		return gson.fromJson(jsonStr, type);
	}
	
	public static <E> String toJson(E obj, Type type) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(obj, type);
	}
	
	public static <E> List<E> fromJson(List<String> jsonStrs, Type type) {
		if(!CollectionUtils.isEmpty(jsonStrs)) {
			List<E> resultList = new ArrayList<>();
			for(String jsonStr:jsonStrs) {
				E e = fromJson(jsonStr, type);
				if(e != null) {
					resultList.add(e);
				}
			}
			return resultList;
		}
		return null;
	}
}
