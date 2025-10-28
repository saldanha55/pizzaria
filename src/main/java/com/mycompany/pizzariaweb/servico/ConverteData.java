package com.mycompany.pizzariaweb.servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe utilitária para conversão de datas.
 */
public class ConverteData {

    /**
     * Converte uma String no formato "yyyy-MM-dd" para um objeto java.util.Date.
     * @param dataString A data em formato de texto.
     * @return Um objeto Date.
     */
    public static java.util.Date convertStringToDate(String dataString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Converte um objeto java.util.Date para um objeto java.sql.Date,
     * que é o formato esperado pelo JDBC para ser salvo no banco de dados.
     * @param utilDate A data do tipo java.util.Date.
     * @return A data no formato java.sql.Date.
     */
    public static java.sql.Date convertDateToSQL(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }
}
