/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author erikv
 */
public class Pizza_por_venda {
    private Integer codigo;
    private Venda venda_codPedido = new Venda(); 
    private Pizza pizza_codPizza =  new Pizza();
    private int quantidade;
    private String tamanho;
    private Double precoVenda;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Venda getVenda_codPedido() {
        return venda_codPedido;
    }

    public void setVenda_codPedido(Venda venda_codPedido) {
        this.venda_codPedido = venda_codPedido;
    }

    public Pizza getPizza_codPizza() {
        return pizza_codPizza;
    }

    public void setPizza_codPizza(Pizza pizza_codPizza) {
        this.pizza_codPizza = pizza_codPizza;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
}
