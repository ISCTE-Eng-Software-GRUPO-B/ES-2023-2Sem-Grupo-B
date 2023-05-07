package com.iscte.engsoft.grupob.calendarapp.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que representa um Evento no formato interno da aplicação (que é o mesmo passado para o
 * frontend para a API do calendário em JavaScript)
 */
@Data
public class EventFrontend {

    public EventFrontend() {
        //Empty constructor
    }

    private String curso;

    private String uc;

    private String turno;

    private String turma;

    private int inscritos;

    private DiaDaSemana diaDaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private LocalDate dataAula;

    private String salaAtribuida;

    private int lotacao;


}
