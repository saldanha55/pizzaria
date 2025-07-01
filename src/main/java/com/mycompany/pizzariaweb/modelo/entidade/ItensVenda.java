/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author tulio
 */
public class ItensVenda {

    private Integer codItensVenda;
    private Integer quantidade;
    private Pizza objItens_codPizza = new Pizza();
    private Venda objItens_codVenda = new Venda();
    private Bebida objItens_codBebida = new Bebida();
    private Double valor;
    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    

    public Integer getCodItensVenda() {
        return codItensVenda;
    }

    public void setCodItensVenda(Integer codItensVenda) {
        this.codItensVenda = codItensVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Pizza getObjItens_codPizza() {
        return objItens_codPizza;
    }

    public void setObjItens_codPizza(Pizza objItens_codPizza) {
        this.objItens_codPizza = objItens_codPizza;
    }

    public Venda getObjItens_codVenda() {
        return objItens_codVenda;
    }

    public void setObjItens_codVenda(Venda objItens_codVenda) {
        this.objItens_codVenda = objItens_codVenda;
    }

    public Bebida getObjItens_codBebida() {
        return objItens_codBebida;
    }

    public void setObjItens_codBebida(Bebida objItens_codBebida) {
        this.objItens_codBebida = objItens_codBebida;
    }

    @Override
    public String toString() {
        return objItens_codPizza.getNome() + " Valor: " + objItens_codPizza.getPrecoBase() + " Quant.: " + String.valueOf(quantidade);
    }

}
