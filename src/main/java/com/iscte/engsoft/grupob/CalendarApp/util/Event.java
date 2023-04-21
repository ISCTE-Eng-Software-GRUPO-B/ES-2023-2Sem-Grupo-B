package com.iscte.engsoft.grupob.CalendarApp.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder({
        "Curso",
        "Unidade Curricular",
        "Turno",
        "Turma",
        "Inscritos no turno",
        "Dia da semana",
        "Hora inicio da aula",
        "Hora fim da aula",
        "Data da aula",
        "Sala atribuida a aula",
        "Lotacao da sala"
})
public abstract class Event {

    @JsonProperty("Curso")
    private String curso;

    @JsonProperty("Unidade Curricular")
    private String uc;

    @JsonProperty("Turno")
    private String turno;

    @JsonProperty("Turma")
    private String turma;

    @JsonProperty("Inscritos no turno")
    private int inscritos;

    @JsonProperty("Dia da semana")
    private DiaDaSemana diaDaSemana;

    @JsonProperty("Hora inicio da aula")
    private LocalTime horaInicio;

    @JsonProperty("Hora fim da aula")
    private LocalTime horaFim;

    @JsonProperty("Data da aula")
    private LocalDate dataAula;

    @JsonProperty("Sala atribuida a aula")
    private String salaAtribuida;

    @JsonProperty("Lotacao da sala")
    private int lotacao;

    public enum DiaDaSemana {
        SEG,TER,QUA,QUI,SEX;
    }
}
