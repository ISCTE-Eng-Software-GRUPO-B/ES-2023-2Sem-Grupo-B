package com.iscte.engsoft.grupob.CalendarApp.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;

public class CSVConverter {

	public void jsonToCSV(String filePath) {
		try {
			File jsonFile = new File(filePath);
			JsonNode jsonTree = new ObjectMapper().readTree(jsonFile);

			Builder csvSchemaBuilder = CsvSchema.builder();
			JsonNode firstObject = jsonTree.elements().next();
			firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);

			CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
			CsvMapper csvMapper = new CsvMapper();
			csvMapper.writerFor(JsonNode.class).with(csvSchema).writeValue(new File("src/convertedFile.csv"), jsonTree);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
