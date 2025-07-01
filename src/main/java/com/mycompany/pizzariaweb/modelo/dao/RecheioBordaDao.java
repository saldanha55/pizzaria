/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class RecheioBordaDao extends GenericoDAO<RecheioBorda>{
    
    public void salvar(RecheioBorda objRecheioBorda){
        String sql = "INSERT INTO recheio_borda(SABOR, PRECO) VALUES(?,?)";
        save(sql, objRecheioBorda.getSaborRecheioBorda(), objRecheioBorda.getPrecoRecheioBorda());
    }
    
    public void alterar(RecheioBorda objRecheioBorda){
        String sql = "UPDATE recheio_borda SET SABOR=?, PRECO=? WHERE CODBORDA=?";
        save(sql, objRecheioBorda.getSaborRecheioBorda(), objRecheioBorda.getPrecoRecheioBorda(), objRecheioBorda.getCodigoRecheioBorda());
    }
    
    public void excluir(RecheioBorda objRecheioBorda){
        String sql = "DELETE FROM recheio_borda WHERE CODBORDA=?";
        save(sql, objRecheioBorda.getCodigoRecheioBorda());
    }
    
    public List<RecheioBorda> buscarTodas(){
        String sql = "SELECT * FROM recheio_borda";
        return buscarTodos(sql, new RecheioBordaRowMapper());
    }
    
    public RecheioBorda buscarPorId(int id){
        String sql = "SELECT * FROM recheio_borda WHERE CODBORDA=?";
        return buscarPorId(sql, new RecheioBordaRowMapper(), id);
    }
    private static class RecheioBordaRowMapper implements RowMapper<RecheioBorda>{
        @Override
        public RecheioBorda mapRow(ResultSet rs) throws SQLException{
            RecheioBorda objRecheioBorda = new RecheioBorda();
            objRecheioBorda.setCodigoRecheioBorda(rs.getInt("codBorda"));
            objRecheioBorda.setSaborRecheioBorda(rs.getString("sabor"));
            objRecheioBorda.setPrecoRecheioBorda(rs.getDouble("preco"));
            return objRecheioBorda;
        }
    }
}
