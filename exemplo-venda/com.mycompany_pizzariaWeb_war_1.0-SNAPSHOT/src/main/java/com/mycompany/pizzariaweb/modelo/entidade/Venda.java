/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author 14830919612
 */
public class Venda {
    private Integer codPedido;
    private Calendar data;
    private String tipoPagamento;
    private Double desconto;
    private Double comissao;
    private Cliente cliente_codCliente = new Cliente();
    private Funcionario funcionario_codFuncionario = new Funcionario();

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
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

    public Cliente getCliente_codCliente() {
        return cliente_codCliente;
    }

    public void setCliente_codCliente(Cliente cliente_codCliente) {
        this.cliente_codCliente = cliente_codCliente;
    }

    public Funcionario getFuncionario_codFuncionario() {
        return funcionario_codFuncionario;
    }

    public void setFuncionario_codFuncionario(Funcionario funcionario_codFuncionario) {
        this.funcionario_codFuncionario = funcionario_codFuncionario;
    }
    
    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data.getTime()); 
   }
    
}
