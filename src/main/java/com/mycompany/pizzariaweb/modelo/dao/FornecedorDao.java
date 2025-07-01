/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 16658144621
 */
public class FornecedorDao extends GenericoDAO<Fornecedor> {

    public void salvar(Fornecedor objFornecedor) {
        String sql = "INSERT INTO FORNECEDOR(NOME, TELEFONE, EMAIL, CPF) VALUES(?,?,?,?)";
        save(sql, objFornecedor.getNomeFornecedor(), objFornecedor.getTelefoneFornecedor(), objFornecedor.getEmailFornecedor(), objFornecedor.getCpfFornecedor());
    }

    public void alterar(Fornecedor objFornecedor) {
        String sql = "UPDATE FORNECEDOR SET NOME=?, TELEFONE=?, EMAIL=?, CPF=? WHERE CODFORNECEDOR=?";
        save(sql, objFornecedor.getNomeFornecedor(), objFornecedor.getTelefoneFornecedor(), objFornecedor.getEmailFornecedor(), objFornecedor.getCpfFornecedor(), objFornecedor.getCodigoFornecedor());
    }

    public void excluir(Fornecedor objFornecedor) {
        String sql = "DELETE FROM FORNECEDOR WHERE CODFORNECEDOR=?";
        save(sql, objFornecedor.getCodigoFornecedor());
    }

    public List<Fornecedor> buscarTodas() {
        String sql = "SELECT * FROM FORNECEDOR";
        return buscarTodos(sql, new FornecedorRowMapper());
    }

    public Fornecedor buscarPorId(int id) {
        String sql = "SELECT * FROM FORNECEDOR WHERE CODFORNECEDOR=?";
        return buscarPorId(sql, new FornecedorRowMapper(), id);
    }

    private static class FornecedorRowMapper implements RowMapper<Fornecedor> {

        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor objFornecedor = new Fornecedor();
            objFornecedor.setCodigoFornecedor(rs.getInt("codFornecedor"));
            objFornecedor.setNomeFornecedor(rs.getString("nome"));
            objFornecedor.setTelefoneFornecedor(rs.getString("telefone"));
            objFornecedor.setEmailFornecedor(rs.getString("email"));
            objFornecedor.setCpfFornecedor(rs.getString("cpf"));
            return objFornecedor;
        }

    }

}
