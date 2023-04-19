package com.iscte.engsoft.grupob.CalendarApp.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CSVConverter {

	ObjectMapper mapper;

	{
		mapper = new ObjectMapper()
				.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
	}

	public boolean isValidJSON(String json) {
		try {
			mapper.readTree(json);
		} catch (JacksonException e) {
			return false;
		}
		return true;
	}

	public String jsonToCSV(String content) {

		if(!isValidJSON(content)) return null;

		String convertedContent;

		try {
			JsonNode jsonTree = new ObjectMapper().readTree(content);
			CsvMapper csvMapper = new CsvMapper();

			CsvSchema csvSchema = csvMapper.schemaFor(ClassForCsv.class).withHeader();

			convertedContent = csvMapper.writerFor(JsonNode.class)
					.with(csvSchema)
					.writeValueAsString(jsonTree);

		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return convertedContent;
	}
}
