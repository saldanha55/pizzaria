/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 16658144621
 */
public class FuncionarioDao extends GenericoDAO<Funcionario> {

    public void salvar(Funcionario objFuncionario) {
        String sql = "INSERT INTO funcionario(NOME, CPF, SALARIO, CARGO, BAIRRO, RUA, NUMERO, TELEFONE, EMAIL) VALUES(?,?,?,?,?,?,?,?,?)";
        save(sql, objFuncionario.getNomeFuncionario(), objFuncionario.getCpfFuncionario(), objFuncionario.getSalarioFuncionario(), objFuncionario.getCargoFuncionario(), objFuncionario.getBairroFuncionario(), objFuncionario.getRuaFuncionario(), objFuncionario.getNumeroFuncionario(), objFuncionario.getTelefoneFuncionario(), objFuncionario.getEmailFuncionario());
    }

    public void alterar(Funcionario objFuncionario) {
        String sql = "UPDATE funcionario SET NOME=?, CPF=?, SALARIO=?, CARGO=?, BAIRRO=?, RUA=?, NUMERO=?, TELEFONE=?, EMAIL=? WHERE CODFUNCIONARIO=?";
        save(sql, objFuncionario.getNomeFuncionario(), objFuncionario.getCpfFuncionario(), objFuncionario.getSalarioFuncionario(), objFuncionario.getCargoFuncionario(), objFuncionario.getBairroFuncionario(), objFuncionario.getRuaFuncionario(), objFuncionario.getNumeroFuncionario(), objFuncionario.getTelefoneFuncionario(), objFuncionario.getEmailFuncionario(), objFuncionario.getCodigoFuncionario());
    }

    public void excluir(Funcionario objFuncionario) {
        String sql = "DELETE FROM funcionario WHERE CODFUNCIONARIO=?";
        save(sql, objFuncionario.getCodigoFuncionario());
    }

    public List<Funcionario> buscarTodas() {
        String sql = "SELECT * FROM funcionario";
        return buscarTodos(sql, new FuncionarioRowMapper());
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE CODFUNCIONARIO=?";
        return buscarPorId(sql, new FuncionarioRowMapper(), id);
    }

    private static class FuncionarioRowMapper implements RowMapper<Funcionario> {

        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario objFuncionario = new Funcionario();
            objFuncionario.setCodigoFuncionario(rs.getInt("codFuncionario"));
            objFuncionario.setNomeFuncionario(rs.getString("nome"));
            objFuncionario.setCpfFuncionario(rs.getString("cpf"));
            objFuncionario.setSalarioFuncionario(rs.getDouble("salario"));
            objFuncionario.setCargoFuncionario(rs.getString("cargo"));
            objFuncionario.setBairroFuncionario(rs.getString("bairro"));
            objFuncionario.setRuaFuncionario(rs.getString("rua"));
            objFuncionario.setNumeroFuncionario(rs.getString("numero"));
            objFuncionario.setTelefoneFuncionario(rs.getString("telefone"));
            objFuncionario.setEmailFuncionario(rs.getString("email"));
            return objFuncionario;
        }

    }

}
