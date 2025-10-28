package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.servico.ConverteData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao implements GenericoDAO<Funcionario> {

    public FuncionarioDao() {
    }

    @Override
    public Funcionario inserir(Funcionario entidade) throws SQLException {
        String sql = "INSERT INTO funcionario (nome, cpf, salario, cargo, bairro, rua, numero, telefone, email, nascimento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setDouble(3, entidade.getSalario());
            stmt.setString(4, entidade.getCargo());
            stmt.setString(5, entidade.getBairro());
            stmt.setString(6, entidade.getRua());
            stmt.setString(7, entidade.getNumero());
            stmt.setString(8, entidade.getTelefone());
            stmt.setString(9, entidade.getEmail());
            // Uso correto do ConverteData para o campo 'nascimento'
            stmt.setDate(10, ConverteData.convertDateToSQL(entidade.getNascimento()));
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodFuncionario(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Funcionario entidade) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, salario = ?, cargo = ?, bairro = ?, " +
                     "rua = ?, numero = ?, telefone = ?, email = ?, nascimento = ? WHERE codFuncionario = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCpf());
            stmt.setDouble(3, entidade.getSalario());
            stmt.setString(4, entidade.getCargo());
            stmt.setString(5, entidade.getBairro());
            stmt.setString(6, entidade.getRua());
            stmt.setString(7, entidade.getNumero());
            stmt.setString(8, entidade.getTelefone());
            stmt.setString(9, entidade.getEmail());
            stmt.setDate(10, ConverteData.convertDateToSQL(entidade.getNascimento()));
            stmt.setInt(11, entidade.getCodFuncionario());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Funcionario entidade) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE codFuncionario = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodFuncionario());
            stmt.executeUpdate();
        }
    }

    @Override
    public Funcionario buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE codFuncionario = ?";
        Funcionario funcionario = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new FuncionarioRowMapper().mapRow(rs);
                }
            }
        }
        return funcionario;
    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                funcionarios.add(new FuncionarioRowMapper().mapRow(rs));
            }
        }
        return funcionarios;
    }

    public static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodFuncionario(rs.getInt("codFuncionario"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setSalario(rs.getDouble("salario"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setRua(rs.getString("rua"));
            funcionario.setNumero(rs.getString("numero"));
            funcionario.setTelefone(rs.getString("telefone"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setNascimento(rs.getDate("nascimento"));
            return funcionario;
        }
    }
    public RelatorioDTO getTopFuncionariosPorVenda() {
    String sql = "SELECT f.nome, COUNT(v.codPedido) as total_vendas " +
                 "FROM venda v JOIN funcionario f ON v.Funcionario_codFuncionario = f.codFuncionario " +
                 "GROUP BY f.nome ORDER BY total_vendas DESC ";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("nome"));
            values.add(rs.getInt("total_vendas"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar top funcionários por venda: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}
}
