/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import com.mycompany.pizzariaweb.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class PizzaDao extends GenericoDAO<Pizza>{
    
    public void salvar(Pizza objPizza){
        String sql = "INSERT INTO pizza(NOME, PRECOBASE, PIZZA_BORDA) VALUES(?,?,?)";
        save(sql, objPizza.getNome(), objPizza.getPrecoBase(), objPizza.getPizza_borda().getCodigoRecheioBorda());
    }
    
    public void alterar(Pizza objPizza){
        String sql = "UPDATE pizza SET NOME=?, PRECOBASE=?, PIZZA_BORDA=? WHERE CODPIZZA=?";
        save(sql, objPizza.getNome(), objPizza.getPrecoBase(), objPizza.getPizza_borda().getCodigoRecheioBorda(), objPizza.getCodPizza());
    }
    
    public void excluir(Pizza objPizza){
        String sql = "DELETE FROM pizza WHERE CODPIZZA=?";
        save(sql, objPizza.getCodPizza());
    }
    
    public List<Pizza> buscarTodas(){
        String sql = "SELECT * FROM pizza";
        return buscarTodos(sql, new PizzaRowMapper());
    }
    
    public Pizza buscarPorId(int id){
        String sql = "SELECT * FROM pizza WHERE CODPIZZA=?";
        return buscarPorId(sql, new PizzaRowMapper(), id);
    }
    
    private static class PizzaRowMapper implements RowMapper<Pizza>{
        
        RecheioBordaDao recheioBordaDao = new RecheioBordaDao();
        
        @Override
        public Pizza mapRow(ResultSet rs) throws SQLException{
            Pizza objPizza = new Pizza();
            objPizza.setCodPizza(rs.getInt("codPizza"));
            objPizza.setNome(rs.getString("nome"));
            objPizza.setPrecoBase(rs.getDouble("precoBase"));
           // objPizza.setPizza_borda(recheioBordaDao.buscarPorId(1));
            objPizza.setPizza_borda(recheioBordaDao.buscarPorId(rs.getInt("Pizza_borda")));
            return objPizza;
        }
    }

}
