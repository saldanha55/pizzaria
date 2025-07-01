/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import com.mycompany.pizzariaweb.servico.ConverteData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 14830919612
 */
public class VendaDao extends GenericoDAO<Venda>{
    
    public void salvar(Venda objVenda){
        String sql = "INSERT INTO venda(DATA, TIPOPAGAMENTO, DESCONTO, COMISSAO, CLIENTE_CODCLIENTE, FUNCIONARIO_CODFUNCIONARIO) VALUES(?,?,?,?,?,?)";
        save(sql, objVenda.getData(), objVenda.getTipoPagamento(), objVenda.getDesconto(), objVenda.getComissao(), objVenda.getCliente_codCliente().getCodigoCliente(), objVenda.getFuncionario_codFuncionario().getCodigoFuncionario());
    }
    
    public void alterar(Venda objVenda){
        String sql = "UPDATE venda SET DATA=?, TIPOPAGAMENTO=?, DESCONTO=?, COMISSAO=?, CLIENTE_CODCLIENTE=?, FUNCIONARIO_CODFUNCIONARIO=? WHERE CODPEDIDO=?";
        save(sql, objVenda.getData(), objVenda.getTipoPagamento(), objVenda.getDesconto(), objVenda.getComissao(), objVenda.getCliente_codCliente().getCodigoCliente(), objVenda.getFuncionario_codFuncionario().getCodigoFuncionario(), objVenda.getCodPedido());
    }
    
    public void excluir(Venda objVenda){
        String sql = "DELETE FROM venda WHERE CODPEDIDO=?";
        save(sql, objVenda.getCodPedido());
    }
    
    public List<Venda> buscarTodas(){
        String sql = "SELECT * FROM venda";
        return buscarTodos(sql, new VendaRowMapper());
    }
    
    public Venda buscarPorId(int id){
        String sql = "SELECT * FROM venda WHERE CODPEDIDO=?";
        return buscarPorId(sql, new VendaRowMapper(), id);
    }
    
    public int getLastId() {
        String sql = "SELECT * FROM venda ORDER BY codPedido DESC LIMIT 1";
        return buscarTodos(sql, new VendaRowMapper()).get(0).getCodPedido();
    }
    
    private static class VendaRowMapper implements RowMapper<Venda>{
        
        ClienteDao clienteDao = new ClienteDao();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        ConverteData converte = new ConverteData();
        
        @Override
        public Venda mapRow(ResultSet rs) throws SQLException{
            Venda objVenda = new Venda();
            objVenda.setCodPedido(rs.getInt("codPedido"));
            objVenda.setData(converte.converteCalendario(rs.getDate("data")));
            objVenda.setTipoPagamento(rs.getString("tipoPagamento"));
            objVenda.setDesconto(rs.getDouble("desconto"));
            objVenda.setComissao(rs.getDouble("comissao"));
            objVenda.setCliente_codCliente(clienteDao.buscarPorId(rs.getInt("Cliente_codCliente")));
            objVenda.setFuncionario_codFuncionario(funcionarioDao.buscarPorId(rs.getInt("Funcionario_codFuncionario")));
            return objVenda;
        }
    }
}
