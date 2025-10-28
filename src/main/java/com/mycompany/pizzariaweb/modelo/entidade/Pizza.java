package com.mycompany.pizzariaweb.modelo.entidade;

public class Pizza {

    private int codPizza;
    private String nome;
    private double precoBase;
    private String tamanho; // Mantido como String para compatibilidade com o seu DAO. ENUM seria uma opção.
    private RecheioBorda borda; // O objeto relacionado

    public Pizza() {
    }

    // Getters e Setters para todos os campos

    public int getCodPizza() {
        return codPizza;
    }

    public void setCodPizza(int codPizza) {
        this.codPizza = codPizza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public RecheioBorda getBorda() {
        return borda;
    }

    public void setBorda(RecheioBorda borda) {
        this.borda = borda;
    }
}
