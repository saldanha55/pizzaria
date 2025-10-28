package com.mycompany.pizzariaweb.modelo.entidade;

public class ItensVenda {

    private int codItensVenda;
    private Venda itens_codVenda;
    private Pizza itens_codPizza;
    private Bebida itens_codBebida;
    private int quantidade;
    private double valor;

    // --- CORREÇÃO ---
    // Getters e setters para todos os campos, incluindo os objetos relacionados.
    // Isso corrige os erros de "método inexistente".

    public int getCodItensVenda() {
        return codItensVenda;
    }

    public void setCodItensVenda(int codItensVenda) {
        this.codItensVenda = codItensVenda;
    }

    public Venda getItens_codVenda() {
        return itens_codVenda;
    }

    public void setItens_codVenda(Venda itens_codVenda) {
        this.itens_codVenda = itens_codVenda;
    }

    public Pizza getItens_codPizza() {
        return itens_codPizza;
    }

    public void setItens_codPizza(Pizza itens_codPizza) {
        this.itens_codPizza = itens_codPizza;
    }

    public Bebida getItens_codBebida() {
        return itens_codBebida;
    }

    public void setItens_codBebida(Bebida itens_codBebida) {
        this.itens_codBebida = itens_codBebida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
