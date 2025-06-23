/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class ClienteDao extends GenericoDAO<Cliente>{
    
    public void salvar(Cliente objCliente){
        String sql = "INSERT INTO cliente(NOME, TELEFONE, CPF, BAIRRO, RUA, NUMERO, EMAIL) VALUES(?,?,?,?,?,?,?)";
        save(sql, objCliente.getNomeCliente(), objCliente.getTelefoneCliente(), objCliente.getCpfCliente(), objCliente.getBairroCliente(), objCliente.getRuaCliente(), objCliente.getNumeroCliente(), objCliente.getEmailCliente());
    }
    
    public void alterar(Cliente objCliente){
        String sql = "UPDATE cliente SET NOME=?, TELEFONE=?, CPF=?, BAIRRO=?, RUA=?, NUMERO=?, EMAIL=? WHERE CODCLIENTE=?";
        save(sql, objCliente.getNomeCliente(), objCliente.getTelefoneCliente(), objCliente.getCpfCliente(), objCliente.getBairroCliente(), objCliente.getRuaCliente(), objCliente.getNumeroCliente(), objCliente.getEmailCliente(), objCliente.getCodigoCliente());
    }
    
    public void excluir(Cliente objCliente){
        String sql = "DELETE FROM cliente WHERE CODCLIENTE=?";
        save(sql, objCliente.getCodigoCliente());
    }
    
    public List<Cliente> buscarTodas(){
        String sql = "SELECT * FROM cliente";
        return buscarTodos(sql, new ClienteRowMapper());
    }
    
    public Cliente buscarPorId(int id){
        String sql = "SELECT * FROM cliente WHERE CODCLIENTE=?";
        return buscarPorId(sql, new ClienteRowMapper(), id);
    }
    private static class ClienteRowMapper implements RowMapper<Cliente>{
        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException{
            Cliente objCliente = new Cliente();
            objCliente.setCodigoCliente(rs.getInt("codCliente"));
            objCliente.setNomeCliente(rs.getString("nome"));
            objCliente.setTelefoneCliente(rs.getString("telefone"));
            objCliente.setCpfCliente(rs.getString("cpf"));
            objCliente.setBairroCliente(rs.getString("bairro"));
            objCliente.setRuaCliente(rs.getString("rua"));
            objCliente.setNumeroCliente(rs.getString("numero"));
            objCliente.setEmailCliente(rs.getString("email"));
            return objCliente;
        }
    }
}
