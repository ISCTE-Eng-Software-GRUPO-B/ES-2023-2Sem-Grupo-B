package com.iscte.engsoft.grupob.CalendarApp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import format.CSV;

public class JSONConverter {

	public void csvToJSON(String filePath) {

		try (InputStream in = new FileInputStream(filePath)) {
			CSV csv = new CSV(true, ',', in);
			List<String> fieldNames;

			List<Map<String, String>> list = new ArrayList<>();

			if (csv.hasNext()) {
				fieldNames = new ArrayList<>(csv.next());

				while (csv.hasNext()) {
					List<String> x = csv.next();
					Map<String, String> obj = new LinkedHashMap<>();

					for (int i = 0; i < x.size(); i++) {
						obj.put(fieldNames.get(i), x.get(i));
					}

					list.add(obj);
				}
				ObjectMapper mapper = new ObjectMapper();
				mapper.enable(SerializationFeature.INDENT_OUTPUT);

				mapper.writeValue(System.out, list);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
