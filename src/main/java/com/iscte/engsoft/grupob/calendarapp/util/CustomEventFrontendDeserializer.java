package com.iscte.engsoft.grupob.calendarapp.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.iscte.engsoft.grupob.calendarapp.model.DiaDaSemana;
import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Esta classe implementa a desserialização do Json do calendário para a estrutura
 * interna de dados que guarda em memória no programa o calendário com a lista de eventos
 */
public class CustomEventFrontendDeserializer extends StdDeserializer<EventFrontend> {

    public CustomEventFrontendDeserializer() {
        this(null);
    }

    public CustomEventFrontendDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public EventFrontend deserialize(JsonParser parser, DeserializationContext deserializer)
    throws IOException {

        EventFrontend eventFrontend = new EventFrontend();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        JsonNode cursoNode = node.get("Curso");
        String curso = cursoNode.asText();
        eventFrontend.setCurso(curso);

        JsonNode ucNode = node.get("Unidade Curricular");
        String uc = ucNode.asText();
        eventFrontend.setUc(uc);

        JsonNode turnoNode = node.get("Turno");
        String turno = turnoNode.asText();
        eventFrontend.setTurno(turno);

        JsonNode turmaNode = node.get("Turma");
        String turma = turmaNode.asText();
        eventFrontend.setTurma(turma);

        JsonNode inscritosNode = node.get("Inscritos no turno");
        if (inscritosNode!=null) {
            int inscritos = inscritosNode.asInt();
            eventFrontend.setInscritos(inscritos);
        }

        JsonNode diaDaSemanaNode = node.get("Dia da semana");
        String diaDaSemana = diaDaSemanaNode.asText().toUpperCase();
        eventFrontend.setDiaDaSemana(DiaDaSemana.valueOf(diaDaSemana));

        JsonNode horaInicioNode = node.get("Hora inicio da aula");
        String horaInicio = horaInicioNode.asText();
        eventFrontend.setHoraInicio(LocalTime.parse(horaInicio, DateTimeFormatter.ofPattern("HH:mm:ss")));

        JsonNode horaFimNode = node.get("Hora fim da aula");
        String horaFim = horaFimNode.asText();
        eventFrontend.setHoraFim(LocalTime.parse(horaFim, DateTimeFormatter.ofPattern("HH:mm:ss")));

        JsonNode dataNode = node.get("Data da aula");
        String data = dataNode.asText();
        eventFrontend.setDataAula(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        JsonNode salaNode = node.get("Sala atribuida a aula");
        String sala = salaNode.asText();
        eventFrontend.setSalaAtribuida(sala);

        JsonNode lotacaoNode = node.get("Lotacao da sala");
        if (lotacaoNode != null) {
            int lotacao = lotacaoNode.asInt();
            eventFrontend.setLotacao(lotacao);
        }

        return eventFrontend;
    }

}
