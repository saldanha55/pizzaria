package com.mycompany.pizzariaweb.modelo.entidade;

import java.util.Date;

public class Venda {

    private int codPedido;
    private Date data;
    private String tipoPagamento;
    private Double desconto;
    private Double comissao;
    private Cliente cliente;
    private Funcionario funcionario;

    // --- CORREÇÃO ---
    // Adicionei um construtor vazio, que é uma boa prática para entidades.
    public Venda() {
    }

    // Getters e Setters para todos os campos.
    // Garante que os DAOs e JSPs possam acessar e modificar os dados da Venda.

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getComissao() {
        return comissao;
    }

    public void setComissao(Double comissao) {
        this.comissao = comissao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
