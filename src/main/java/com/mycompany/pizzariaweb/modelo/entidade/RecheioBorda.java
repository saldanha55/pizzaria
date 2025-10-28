package com.mycompany.pizzariaweb.modelo.entidade;

public class RecheioBorda {

    private int codBorda;
    private String sabor;
    private double preco;

    public RecheioBorda() {
    }

    // Getters e Setters para todos os campos

    public int getCodBorda() {
        return codBorda;
    }

    public void setCodBorda(int codBorda) {
        this.codBorda = codBorda;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
