/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author 14830919612
 */
public class Ingrediente_por_pizza {
    private Integer cod;
    private Pizza pizza_codPizza = new Pizza();
    private Ingrediente ingrediente_codIngrediente = new Ingrediente();
    private float quantIngrediente;

    public Integer getCod() {
        return cod;
    }

    public void setCodigo(Integer cod) {
        this.cod = cod;
    }

    public Pizza getPizza_codPizza() {
        return pizza_codPizza;
    }

    public void setPizza_codPizza(Pizza pizza_codPizza) {
        this.pizza_codPizza = pizza_codPizza;
    }

    public Ingrediente getIngrediente_codIngrediente() {
        return ingrediente_codIngrediente;
    }

    public void setIngrediente_codIngrediente(Ingrediente ingrediente_codIngrediente) {
        this.ingrediente_codIngrediente = ingrediente_codIngrediente;
    }

    public float getQuantIngrediente() {
        return quantIngrediente;
    }

    public void setQuantIngrediente(float quantIngrediente) {
        this.quantIngrediente = quantIngrediente;
    }

    
    
    
}
