package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao implements GenericoDAO<Fornecedor> {

    public FornecedorDao() {
    }

    @Override
    public Fornecedor inserir(Fornecedor entidade) throws SQLException {
        String sql = "INSERT INTO fornecedor (nome, telefone, email, cpf) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getEmail());
            stmt.setString(4, entidade.getCpf());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodFornecedor(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Fornecedor entidade) throws SQLException {
        String sql = "UPDATE fornecedor SET nome = ?, telefone = ?, email = ?, cpf = ? WHERE codFornecedor = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getEmail());
            stmt.setString(4, entidade.getCpf());
            stmt.setInt(5, entidade.getCodFornecedor());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Fornecedor entidade) throws SQLException {
        String sql = "DELETE FROM fornecedor WHERE codFornecedor = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodFornecedor());
            stmt.executeUpdate();
        }
    }

    @Override
    public Fornecedor buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM fornecedor WHERE codFornecedor = ?";
        Fornecedor fornecedor = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    fornecedor = new FornecedorRowMapper().mapRow(rs);
                }
            }
        }
        return fornecedor;
    }

    @Override
    public List<Fornecedor> listar() throws SQLException {
        String sql = "SELECT * FROM fornecedor";
        List<Fornecedor> fornecedores = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                fornecedores.add(new FornecedorRowMapper().mapRow(rs));
            }
        }
        return fornecedores;
    }

    public static class FornecedorRowMapper implements RowMapper<Fornecedor> {
        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setCodFornecedor(rs.getInt("codFornecedor"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEmail(rs.getString("email"));
            fornecedor.setCpf(rs.getString("cpf"));
            return fornecedor;
        }
    }
}
