package com.bean.JSON_To_Java;

import java.io.IOException;

import com.bean.Entity.Country;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CountryDeserializer extends StdDeserializer<Country> {

	protected CountryDeserializer() {
		this(null);
	}

	protected CountryDeserializer(Class<?> vc) {
		super(vc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Country deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub

		JsonNode node = jp.getCodec().readTree(jp);

		String Country = node.get("Country").asText();
		String CountryCode = node.get("CountryCode").asText();
		String Province = node.get("Province").asText();
		String City = node.get("City").asText();
		String CityCode = node.get("CityCode").asText();
		String Lat = node.get("Lat").asText();
		String Lon = node.get("Lon").asText();
		String Confirmed = node.get("Confirmed").asText();
		String Deaths = node.get("Deaths").asText();
		String Recovered = node.get("Recovered").asText();
		String Active = node.get("Active").asText();
		String Date = node.get("Date").asText();

		return new Country(Country, CountryCode, Province, City, CityCode, Lat, Lon, Confirmed, Deaths, Recovered,
				Active, Date);
	}

}
