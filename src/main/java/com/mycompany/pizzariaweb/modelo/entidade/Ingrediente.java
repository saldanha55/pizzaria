package com.mycompany.pizzariaweb.modelo.entidade;

public class Ingrediente {

    private int codIngrediente;
    private String nome;
    private double preco;
    private float quantEstoque;

    public Ingrediente() {
    }

    // Getters e Setters para todos os campos

    public int getCodIngrediente() {
        return codIngrediente;
    }

    public void setCodIngrediente(int codIngrediente) {
        this.codIngrediente = codIngrediente;
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

    public float getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(float quantEstoque) {
        this.quantEstoque = quantEstoque;
    }
}
