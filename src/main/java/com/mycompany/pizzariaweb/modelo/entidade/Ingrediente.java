/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author 11997803674
 */
public class Ingrediente {
    private Integer codigoIngrediente;
    private String nomeIngrediente;
    private double precoIngrediente;
    private float quantidadeEstoqueIngrediente;

    public Integer getCodigoIngrediente() {
        return codigoIngrediente;
    }

    public void setCodigoIngrediente(Integer codigoIngrediente) {
        this.codigoIngrediente = codigoIngrediente;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public double getPrecoIngrediente() {
        return precoIngrediente;
    }

    public void setPrecoIngrediente(double precoIngrediente) {
        this.precoIngrediente = precoIngrediente;
    }

    public float getQuantidadeEstoqueIngrediente() {
        return quantidadeEstoqueIngrediente;
    }

    public void setQuantidadeEstoqueIngrediente(float quantidadeEstoqueIngrediente) {
        this.quantidadeEstoqueIngrediente = quantidadeEstoqueIngrediente;
    }

    
}
