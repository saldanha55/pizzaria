package com.mycompany.pizzariaweb.modelo.entidade;

public class Bebida {

    private int codBebida;
    private String nome;
    private double preco;
    private int quantidade;

    public Bebida() {
    }

    // Getters e Setters para todos os campos

    public int getCodBebida() {
        return codBebida;
    }

    public void setCodBebida(int codBebida) {
        this.codBebida = codBebida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
