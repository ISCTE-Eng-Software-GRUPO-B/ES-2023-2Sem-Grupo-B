package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;
import lombok.extern.log4j.Log4j2;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.StringTokenizer;

@Log4j2
public class VEvent2EventConverter {

    private VEvent2EventConverter() {}

    public static EventFrontend desserialize(CalendarComponent component) {

        EventFrontend event = new EventFrontend();

        Optional<Property> dtstart = component.getProperty("DTSTART");
        Optional<Property> dtend = component.getProperty("DTEND");
        Optional<Property> summary = component.getProperty("SUMMARY");
        Optional<Property> location = component.getProperty("LOCATION");
        Optional<Property> descricao = component.getProperty("DESCRIPTION");

        try {

            dtstart.ifPresent( d -> {
                    String s = d.getValue();
                    LocalTime startTime = LocalTime.parse(s.substring(9,15), DateTimeFormatter.ofPattern(DateConverter.TIME_FORMAT));
                    LocalDate localDate = LocalDate.parse(s.substring(0,8), DateTimeFormatter.ofPattern(DateConverter.DATE_FORMAT));
                    event.setDataAula(localDate);
                    event.setHoraInicio(startTime);
                }
            );


            dtend.ifPresent( d -> {
                    String s = d.getValue();
                    LocalTime endTime = LocalTime.parse(s.substring(9,15), DateTimeFormatter.ofPattern(DateConverter.TIME_FORMAT));
                    event.setHoraFim(endTime);
                }
            );

            summary.ifPresent( d -> {
                    String s = d.getValue();
                    event.setUc(s);
                }
            );

            location.ifPresent( d -> {
                    String s = d.getValue();
                    event.setSalaAtribuida(s);
                }
            );

            descricao.ifPresent(d -> {
                    String s = d.getValue();
                    int idxStart = s.indexOf("Turno:")+6;
                    if (idxStart > 5) { // 5 = -1+6 --> NOT FOUND
                        String aux = s.substring(idxStart);
                        StringTokenizer tokenizer = new StringTokenizer(aux, "\n");
                        if (tokenizer.hasMoreTokens()) {
                            String turno = tokenizer.nextToken();
                            event.setTurno(turno);
                        }
                    }
                }
            );

        } catch (IndexOutOfBoundsException ex) {
            log.info(ex.getClass()+" :"+ex.getMessage()+": "+component.toString());
        }

        return event;
    }

}
