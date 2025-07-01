/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Pizza_por_venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class Pizza_por_vendaDao extends GenericoDAO<Pizza_por_venda>{
    
    public void salvar(Pizza_por_venda objPizza_por_venda){
        String sql = "INSERT PIZZA_POR_VENDA(VENDA_CODPEDIDO, PIZZA_CODPIZZA, QUANTIDADE, TAMANHO, PRECOVENDA) VALUES(?,?,?,?,?)";
        save(sql, objPizza_por_venda.getVenda_codPedido().getCodPedido(), objPizza_por_venda.getPizza_codPizza().getCodPizza(), objPizza_por_venda.getQuantidade(), objPizza_por_venda.getTamanho(), objPizza_por_venda.getPrecoVenda());
    }
    
    public void alterar(Pizza_por_venda objPizza_por_venda){
        String sql = "UPDATE PIZZA_POR_VENDA SET VENDA_CODPEDIDO=?, PIZZA_CODPIZZA=?, QUANTIDADE=?, TAMANHO=?, PRECOVENDA=? WHERE CODIGO=?";
        save(sql, objPizza_por_venda.getVenda_codPedido().getCodPedido(), objPizza_por_venda.getPizza_codPizza().getCodPizza(), objPizza_por_venda.getQuantidade(), objPizza_por_venda.getTamanho(), objPizza_por_venda.getPrecoVenda(), objPizza_por_venda.getCodigo());
    }
    
    public void excluir(Pizza_por_venda objPizza_por_venda){
        String sql = "DELETE FROM PIZZA_POR_VENDA WHERE CODIGO=?";
        save(sql, objPizza_por_venda.getCodigo());
    }
    
    public List<Pizza_por_venda> buscarTodas(){
        String sql = "SELECT * FROM PIZZA_POR_VENDA";
        return buscarTodos(sql, new Pizza_por_vendaRowMapper());
    }
    
    public Pizza_por_venda buscarPorId(int id){
        String sql = "SELECT * FROM PIZZA_POR_VENDA WHERE CODIGO=?";
        return buscarPorId(sql, new Pizza_por_vendaRowMapper(), id);
    }
    private static class Pizza_por_vendaRowMapper implements RowMapper<Pizza_por_venda>{
        
        VendaDao vendaDao = new VendaDao();
        PizzaDao pizzaDao = new PizzaDao();
        
        @Override
        public Pizza_por_venda mapRow(ResultSet rs) throws SQLException{
            Pizza_por_venda objPizza_por_venda = new Pizza_por_venda();
            objPizza_por_venda.setCodigo(rs.getInt("codigo"));
            objPizza_por_venda.setVenda_codPedido(vendaDao.buscarPorId(rs.getInt("Venda_codPedido")));
            objPizza_por_venda.setPizza_codPizza(pizzaDao.buscarPorId(rs.getInt("Pizza_codPizza")));
            objPizza_por_venda.setQuantidade(rs.getInt("quantidade"));
            objPizza_por_venda.setTamanho(rs.getString("tamanho"));
            objPizza_por_venda.setPrecoVenda(rs.getDouble("precoVenda"));
            return objPizza_por_venda;
        }
    }
}
