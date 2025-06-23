/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author erikv
 */
public class Pizza {
    private Integer codPizza;
    private String nome;
    private Double precoBase;
    private RecheioBorda pizza_borda = new RecheioBorda();

    public Integer getCodPizza() {
        return codPizza;
    }

    public void setCodPizza(Integer codPizza) {
        this.codPizza = codPizza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    public RecheioBorda getPizza_borda() {
        return pizza_borda;
    }

    public void setPizza_borda(RecheioBorda pizza_borda) {
        this.pizza_borda = pizza_borda;
    }
    
}
