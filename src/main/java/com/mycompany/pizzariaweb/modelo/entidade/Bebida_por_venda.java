/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

/**
 *
 * @author erikv
 */
public class Bebida_por_venda {
    private Integer codigo;
    private Venda venda_codPedido = new Venda(); 
    private Bebida bebida_codBebida =  new Bebida();
    private int quantidade;
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

    public Bebida getBebida_codBebida() {
        return bebida_codBebida;
    }

    public void setBebida_codBebida(Bebida bebida_codBebida) {
        this.bebida_codBebida = bebida_codBebida;
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
