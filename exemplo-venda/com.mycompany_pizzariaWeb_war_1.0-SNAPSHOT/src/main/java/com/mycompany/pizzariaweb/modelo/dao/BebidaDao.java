/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Bebida;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class BebidaDao extends GenericoDAO<Bebida>{
    
    public void salvar(Bebida objBebida){
        String sql = "INSERT INTO BEBIDA(NOME, PRECO, QUANTIDADE) VALUES(?,?,?)";
        save(sql, objBebida.getNomeBebida(), objBebida.getPrecoBebida(), objBebida.getQuantidadeBebida());
    }
    
    public void alterar(Bebida objBebida){
        String sql = "UPDATE BEBIDA SET NOME=?, PRECO=?, QUANTIDADE=? WHERE CODBEBIDA=?";
        save(sql, objBebida.getNomeBebida(), objBebida.getPrecoBebida(), objBebida.getQuantidadeBebida(), objBebida.getCodigoBebida());
    }
    
    public void excluir(Bebida objBebida){
        String sql = "DELETE FROM BEBIDA WHERE CODBEBIDA=?";
        save(sql, objBebida.getCodigoBebida());
    }
    
    public List<Bebida> buscarTodas(){
        String sql = "SELECT * FROM BEBIDA";
        return buscarTodos(sql, new BebidaRowMapper());
    }
    
    public Bebida buscarPorId(int id){
        String sql = "SELECT * FROM BEBIDA WHERE CODBEBIDA=?";
        return buscarPorId(sql, new BebidaRowMapper(), id);
    }
    private static class BebidaRowMapper implements RowMapper<Bebida>{
        @Override
        public Bebida mapRow(ResultSet rs) throws SQLException{
            Bebida objBebida = new Bebida();
            objBebida.setCodigoBebida(rs.getInt("codBebida"));
            objBebida.setNomeBebida(rs.getString("nome"));
            objBebida.setPrecoBebida(rs.getDouble("preco"));
            objBebida.setQuantidadeBebida(rs.getInt("quantidade"));
            return objBebida;
        }
    }
}
