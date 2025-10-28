package com.mycompany.pizzariaweb.modelo.dto;

import java.util.List;

/**
 * DTO (Data Transfer Object) Padrão para carregar dados de relatórios.
 * O tipo de 'values' é List<Object> para ser flexível para exportações
 * (Excel/PDF) que podem conter tipos de dados mistos.
 */
public class RelatorioDTO {
    private List<String> labels;
    private List<Object> values; // O tipo base é List<Object>

    /**
     * Construtor único e principal.
     * @param labels
     * @param values
     */
    public RelatorioDTO(List<String> labels, List<Object> values) {
        this.labels = labels;
        this.values = values;
    }

    // Getters e Setters
    
    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}