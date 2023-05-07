package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.CalendarComponent;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe estende a classe UrlProcessor para fazer o parsing de conteúdo WEBCAL
 * */
public class UrlProcessorWebcal extends UrlProcessor{

    public List<EventFrontend> parseUrlContent(String content) throws IOException {

        List<EventFrontend> output;
        try {
            output = new ArrayList<>();

            StringReader sin = new StringReader(content);
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(sin);
            List<CalendarComponent> list = calendar.getComponents(Component.VEVENT);
            for (CalendarComponent obj : list) {
                output.add(VEvent2EventConverter.desserialize(obj));
            }
        } catch (ParserException e) {
            throw new IOException(e.getMessage());
        }

        return output;

    }

}
