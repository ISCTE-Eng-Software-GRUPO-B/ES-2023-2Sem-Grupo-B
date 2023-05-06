package com.iscte.engsoft.grupob.calendarapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

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
@Builder
@Jacksonized
public class Event {

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

}
