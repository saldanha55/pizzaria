/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente_por_pizza;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class Ingrediente_por_pizzaDao extends GenericoDAO<Ingrediente_por_pizza>{
    
    public void salvar(Ingrediente_por_pizza objIngrediente_por_pizza){
        String sql = "INSERT INTO INGREDIENTE_POR_PIZZA(PIZZA_CODPIZZA, INGREDIENTE_CODINGREDIENTE, QUANTINGREDIENTE) VALUES(?,?,?)";
        save(sql, objIngrediente_por_pizza.getPizza_codPizza().getCodPizza(), objIngrediente_por_pizza.getIngrediente_codIngrediente().getCodigoIngrediente(), objIngrediente_por_pizza.getQuantIngrediente());
    }
    
    public void alterar(Ingrediente_por_pizza objIngrediente_por_pizza){
        String sql = "UPDATE INGREDIENTE_POR_PIZZA SET PIZZA_CODPIZZA=?, INGREDIENTE_CODINGREDIENTE=?, QUANTINGREDIENTE=? WHERE CODIGO=?";
        save(sql, objIngrediente_por_pizza.getPizza_codPizza().getCodPizza(), objIngrediente_por_pizza.getIngrediente_codIngrediente().getCodigoIngrediente(), objIngrediente_por_pizza.getQuantIngrediente(), objIngrediente_por_pizza.getCodigo());
    }
    
    public void excluir(Ingrediente_por_pizza objIngrediente_por_pizza){
        String sql = "DELETE FROM INGREDIENTE_POR_PIZZA WHERE CODIGO=?";
        save(sql, objIngrediente_por_pizza.getCodigo());
    }
    
    public List<Ingrediente_por_pizza> buscarTodas(){
        String sql = "SELECT * FROM INGREDIENTE_POR_PIZZA";
        return buscarTodos(sql, new Ingrediente_por_pizzaRowMapper());
    }
    
    public Ingrediente_por_pizza buscarPorId(int id){
        String sql = "SELECT * FROM INGREDIENTE_POR_PIZZA WHERE CODIGO=?";
        return buscarPorId(sql, new Ingrediente_por_pizzaRowMapper(), id);
    }
    
    private static class Ingrediente_por_pizzaRowMapper implements RowMapper<Ingrediente_por_pizza>{
        
        PizzaDao pizzaDao = new PizzaDao();
        IngredienteDao ingredienteDao = new IngredienteDao();
        
        @Override
        public Ingrediente_por_pizza mapRow(ResultSet rs) throws SQLException{
            Ingrediente_por_pizza objIngrediente_por_pizza = new Ingrediente_por_pizza();
            objIngrediente_por_pizza.setCodigo(rs.getInt("codigo"));
            objIngrediente_por_pizza.setPizza_codPizza(pizzaDao.buscarPorId(rs.getInt("Pizza_codPizza")));
            objIngrediente_por_pizza.setIngrediente_codIngrediente(ingredienteDao.buscarPorId(rs.getInt("Ingrediente_codIngrediente")));
            objIngrediente_por_pizza.setQuantIngrediente(rs.getFloat("quantIngrediente"));
            return objIngrediente_por_pizza;
        }
    }
}
