package com.iscte.engsoft.grupob.CalendarApp.util;

public class FileConverter {
	
	private String getFileExtension(String filePath) {
		int lastIndexOf = filePath.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return "";
		}
		return filePath.substring(lastIndexOf);
	}

	public boolean isCSV(String filePath) {
		return getFileExtension(filePath).equals(".csv");
	}

	public boolean isJSON(String filePath) {
		return getFileExtension(filePath).equals(".json");
	}

	public void work(String filePath) {

		if (isCSV(filePath)) {
			new JSONConverter().csvToJSON(filePath);
			return;
		}

		if (isJSON(filePath)) {
			new CSVConverter().jsonToCSV(filePath);
			return;
		}
		
		System.out.println("File format not recognized: try .json or .csv");
	}

	// "C:\\Users\\soare\\Desktop\\horario-exemplo.csv"
	// "C:\\Users\\soare\\Desktop\\teste.json"
	public static void main(String[] args) {
		new FileConverter().work("C:\\Users\\soare\\Desktop\\horario-exemplo.csv");
	}
}
