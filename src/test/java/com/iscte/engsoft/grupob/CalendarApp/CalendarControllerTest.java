package com.iscte.engsoft.grupob.CalendarApp;

import com.iscte.engsoft.grupob.CalendarApp.controller.CalendarController;
import com.iscte.engsoft.grupob.CalendarApp.model.CalendarFormat;
import com.iscte.engsoft.grupob.CalendarApp.model.ConsumeURLCalendarRequest;
import com.iscte.engsoft.grupob.CalendarApp.utils.UrlReader;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

@SpringBootTest
@Log4j2
class CalendarControllerTest {

	@Mock
	private UrlReader urlReader;

	//@Autowired
	@InjectMocks
	private CalendarController controller;

	@Test
	void consumeUrl() throws IOException {

		ConsumeURLCalendarRequest request =
				ConsumeURLCalendarRequest.builder()
						.url("https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json")
						.type(CalendarFormat.JSON).build();

		/*
		Mockito.verify(urlReader)
				.readFileFromUrl(
						"https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json",
						CalendarFormat.JSON
				);

		 */

		Mockito.when(urlReader.readFileFromUrl(
				"https://raw.githubusercontent.com/bahamas10/css-color-names/master/css-color-names.json")).thenReturn("XPTO");

		String resultJson = controller.consumeUrl(request);
		log.info(String.format("resultJson: %s", resultJson));

		assertEquals("XPTO", resultJson);

	}

}
