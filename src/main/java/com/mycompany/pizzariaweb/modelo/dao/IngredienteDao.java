package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDao implements GenericoDAO<Ingrediente> {

    public IngredienteDao() {
    }

    @Override
    public Ingrediente inserir(Ingrediente entidade) throws SQLException {
        String sql = "INSERT INTO ingrediente (nome, preco, quantEstoque) VALUES (?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setFloat(3, entidade.getQuantEstoque());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodIngrediente(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Ingrediente entidade) throws SQLException {
        String sql = "UPDATE ingrediente SET nome = ?, preco = ?, quantEstoque = ? WHERE codIngrediente = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setFloat(3, entidade.getQuantEstoque());
            stmt.setInt(4, entidade.getCodIngrediente());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Ingrediente entidade) throws SQLException {
        String sql = "DELETE FROM ingrediente WHERE codIngrediente = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodIngrediente());
            stmt.executeUpdate();
        }
    }

    @Override
    public Ingrediente buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM ingrediente WHERE codIngrediente = ?";
        Ingrediente ingrediente = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ingrediente = new IngredienteRowMapper().mapRow(rs);
                }
            }
        }
        return ingrediente;
    }

    @Override
    public List<Ingrediente> listar() throws SQLException {
        String sql = "SELECT * FROM ingrediente";
        List<Ingrediente> ingredientes = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ingredientes.add(new IngredienteRowMapper().mapRow(rs));
            }
        }
        return ingredientes;
    }

    public static class IngredienteRowMapper implements RowMapper<Ingrediente> {
        @Override
        public Ingrediente mapRow(ResultSet rs) throws SQLException {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setCodIngrediente(rs.getInt("codIngrediente"));
            ingrediente.setNome(rs.getString("nome"));
            ingrediente.setPreco(rs.getDouble("preco"));
            ingrediente.setQuantEstoque(rs.getFloat("quantEstoque"));
            return ingrediente;
        }
    }
}
