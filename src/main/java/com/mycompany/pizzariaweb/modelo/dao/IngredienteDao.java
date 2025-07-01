/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class IngredienteDao extends GenericoDAO<Ingrediente>{
    
    public void salvar(Ingrediente objIngrediente){
        String sql = "INSERT INTO INGREDIENTE(NOME, PRECO, QUANTESTOQUE) VALUES(?,?,?)";
        save(sql, objIngrediente.getNomeIngrediente(), objIngrediente.getPrecoIngrediente(), objIngrediente.getQuantidadeEstoqueIngrediente());
    }
    
    public void alterar(Ingrediente objIngrediente){
        String sql = "UPDATE INGREDIENTE SET NOME=?, PRECO=?, QUANTESTOQUE=? WHERE CODINGREDIENTE=?";
        save(sql, objIngrediente.getNomeIngrediente(), objIngrediente.getPrecoIngrediente(), objIngrediente.getQuantidadeEstoqueIngrediente(), objIngrediente.getCodigoIngrediente());
    }
    
    public void excluir(Ingrediente objIngrediente){
        String sql = "DELETE FROM INGREDIENTE WHERE CODINGREDIENTE=?";
        save(sql, objIngrediente.getCodigoIngrediente());
    }
    
    public List<Ingrediente> buscarTodas(){
        String sql = "SELECT * FROM INGREDIENTE";
        return buscarTodos(sql, new IngredienteRowMapper());
    }
    
    public Ingrediente buscarPorId(int id){
        String sql = "SELECT * FROM INGREDIENTE WHERE CODINGREDIENTE=?";
        return buscarPorId(sql, new IngredienteRowMapper(), id);
    }
    private static class IngredienteRowMapper implements RowMapper<Ingrediente>{
        @Override
        public Ingrediente mapRow(ResultSet rs) throws SQLException{
            Ingrediente objIngrediente = new Ingrediente();
            objIngrediente.setCodigoIngrediente(rs.getInt("codIngrediente"));
            objIngrediente.setNomeIngrediente(rs.getString("nome"));
            objIngrediente.setPrecoIngrediente(rs.getDouble("preco"));
            objIngrediente.setQuantidadeEstoqueIngrediente(rs.getFloat("quantEstoque"));
            return objIngrediente;
        }
    }
}
