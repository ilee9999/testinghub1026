package com.hkesports.matchticker.model.json.factory;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hkesports.matchticker.common.utils.DateUtils;

/**
 * @author manboyu
 *
 * @param <T>
 */
public class CustomDateDeserializer implements JsonDeserializer<Date> {
	
	private static final Log logger = LogFactory.getLog(CustomDateDeserializer.class);

	private String dateFormat;
	
	public CustomDateDeserializer(String dateFormat){
		this.dateFormat = dateFormat;
	}
	
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		String dateString = json.getAsString().replaceAll(":", "");
		if(StringUtils.equals("0", dateString)){
			return null;
		}
		try {
			return DateUtils.parserStringToDate(dateString, dateFormat);
		} catch (ParseException e) {
			logger.error("Unparseable date: \"" + json.getAsString() + "\". Supported formats: " + this.dateFormat, e);
		}
		throw new JsonParseException("Unparseable date: \"" + json.getAsString() + "\". Supported formats: " + this.dateFormat);
	}

}
