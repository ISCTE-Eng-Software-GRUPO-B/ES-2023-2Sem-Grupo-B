package com.iscte.engsoft.grupob.CalendarApp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONConverter {

	private JSONConverter(){}

	public static boolean isValidCSV(String content) {
		String header = content.substring(0,content.indexOf("\n"));
		String headerFormat = "Curso,\"Unidade Curricular\",Turno,Turma,\"Inscritos no turno\",\"Dia da semana\",\"Hora inicio da aula\",\"Hora fim da aula\",\"Data da aula\",\"Sala atribuida a aula\",\"Lotacao da sala\"";
		int n = header.compareTo(headerFormat);
		return n == 0;
	}

	public static String csvToJSON(String content) {

		if(!isValidCSV(content)) return null;
		
		String convertedContent = "";

		try {
			CSV csv = new CSV(true, ',', content);

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
				convertedContent = saveConvertedContentToVariable(list);
			}

		} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}

		return convertedContent;
	}

	public static String saveConvertedContentToVariable(List<Map<String, String>> list){
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			return mapper.writeValueAsString(list);
			
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}