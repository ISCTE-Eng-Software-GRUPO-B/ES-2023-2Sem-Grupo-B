package com.iscte.engsoft.grupob.CalendarApp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.util.Optional;

public class CSVConverter {

	private CSVConverter(){}

	public static boolean isValidJSON(String json) {
		ObjectMapper mapper = new ObjectMapper()
					.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
		try {
			mapper.readTree(json);
		} catch (JacksonException e) {
			return false;
		}
		return true;
	}

	public static String jsonToCSV(String content) {

		if(!isValidJSON(content)){ return ""; }

		String convertedContent;

		try {
			JsonNode jsonTree = new ObjectMapper().readTree(content);
			CsvMapper csvMapper = new CsvMapper();

			CsvSchema csvSchema = csvMapper.schemaFor(Event.class).withHeader();

			convertedContent = csvMapper.writerFor(JsonNode.class)
					.with(csvSchema)
					.writeValueAsString(jsonTree);

		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}

		return convertedContent;
	}
}
