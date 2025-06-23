/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Bebida_por_venda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class Bebida_por_vendaDao extends GenericoDAO<Bebida_por_venda>{
    
    public void salvar(Bebida_por_venda objBebida_por_venda){
        String sql = "INSERT INTO BEBIDA_POR_VENDA(VENDA_CODPEDIDO, BEBIDA_CODBEBIDA, QUANTIDADE, PRECOVENDA) VALUES(?,?,?,?)";
        save(sql, objBebida_por_venda.getVenda_codPedido().getCodPedido(), objBebida_por_venda.getBebida_codBebida().getCodigoBebida(), objBebida_por_venda.getQuantidade(), objBebida_por_venda.getPrecoVenda());
    }
    
    public void alterar(Bebida_por_venda objBebida_por_venda){
        String sql = "UPDATE BEBIDA_POR_VENDA SET VENDA_CODPEDIDO=?, BEBIDA_CODBEBIDA=?, QUANTIDADE=?, PRECOVENDA=? WHERE CODIGO=?";
        save(sql, objBebida_por_venda.getVenda_codPedido().getCodPedido(), objBebida_por_venda.getBebida_codBebida().getCodigoBebida(), objBebida_por_venda.getQuantidade(), objBebida_por_venda.getPrecoVenda(), objBebida_por_venda.getCodigo());
    }
    
    public void excluir(Bebida_por_venda objBebida_por_venda){
        String sql = "DELETE FROM BEBIDA_POR_VENDA WHERE CODIGO=?";
        save(sql, objBebida_por_venda.getCodigo());
    }
    
    public List<Bebida_por_venda> buscarTodas(){
        String sql = "SELECT * FROM BEBIDA_POR_VENDA";
        return buscarTodos(sql, new Bebida_por_vendaRowMapper());
    }
    
    public Bebida_por_venda buscarPorId(int id){
        String sql = "SELECT * FROM BEBIDA_POR_VENDA WHERE CODIGO=?";
        return buscarPorId(sql, new Bebida_por_vendaRowMapper(), id);
    }
    private static class Bebida_por_vendaRowMapper implements RowMapper<Bebida_por_venda>{
        
        VendaDao vendaDao = new VendaDao();
        BebidaDao bebidaDao = new BebidaDao();
        
        @Override
        public Bebida_por_venda mapRow(ResultSet rs) throws SQLException{
            Bebida_por_venda objBebida_por_venda = new Bebida_por_venda();
            objBebida_por_venda.setCodigo(rs.getInt("codigo"));
            objBebida_por_venda.setVenda_codPedido(vendaDao.buscarPorId(rs.getInt("Venda_codPedido")));
            objBebida_por_venda.setBebida_codBebida(bebidaDao.buscarPorId(rs.getInt("Bebida_codBebida")));
            objBebida_por_venda.setQuantidade(rs.getInt("quantidade"));
            objBebida_por_venda.setPrecoVenda(rs.getDouble("precoVenda"));
            return objBebida_por_venda;
        }
    }
}
