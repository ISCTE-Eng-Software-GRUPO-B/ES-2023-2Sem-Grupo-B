package com.iscte.engsoft.grupob.calendarapp.util;

import com.iscte.engsoft.grupob.calendarapp.model.EventFrontend;

import java.io.IOException;
import java.util.List;

/**
 * Esta classe abstracta define o método abstracto que faz o parse do conteúdo de um URL.
 * Deverá ser criada uma subclasse por cada tipo de URL a fazer o parsing (WEBCAL, JSON ou CSV)
 * */
public abstract class UrlProcessor {

    /**
     * Faz o parsing da URL
     * @param content recebe uma string com o conteúdo lido da URL
     * @return devolve uma lista de eventos (classe EventFrontend)
     * */
    public abstract List<EventFrontend> parseUrlContent(String content) throws IOException;


}
