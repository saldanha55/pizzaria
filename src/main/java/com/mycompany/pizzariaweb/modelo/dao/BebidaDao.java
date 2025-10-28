package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.modelo.entidade.Bebida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BebidaDao implements GenericoDAO<Bebida> {

    public BebidaDao() {
    }

    @Override
    public Bebida inserir(Bebida entidade) throws SQLException {
        String sql = "INSERT INTO bebida (nome, preco, quantidade) VALUES (?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setInt(3, entidade.getQuantidade());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodBebida(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Bebida entidade) throws SQLException {
        String sql = "UPDATE bebida SET nome = ?, preco = ?, quantidade = ? WHERE codBebida = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setInt(3, entidade.getQuantidade());
            stmt.setInt(4, entidade.getCodBebida());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Bebida entidade) throws SQLException {
        String sql = "DELETE FROM bebida WHERE codBebida = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodBebida());
            stmt.executeUpdate();
        }
    }

    @Override
    public Bebida buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM bebida WHERE codBebida = ?";
        Bebida bebida = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bebida = new BebidaRowMapper().mapRow(rs);
                }
            }
        }
        return bebida;
    }

    @Override
    public List<Bebida> listar() throws SQLException {
        String sql = "SELECT * FROM bebida";
        List<Bebida> bebidas = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                bebidas.add(new BebidaRowMapper().mapRow(rs));
            }
        }
        return bebidas;
    }

    public static class BebidaRowMapper implements RowMapper<Bebida> {
        @Override
        public Bebida mapRow(ResultSet rs) throws SQLException {
            Bebida bebida = new Bebida();
            bebida.setCodBebida(rs.getInt("codBebida"));
            bebida.setNome(rs.getString("nome"));
            bebida.setPreco(rs.getDouble("preco"));
            bebida.setQuantidade(rs.getInt("quantidade"));
            return bebida;
        }
    }
    public RelatorioDTO getBebidasMaisVendidas() {
    String sql = "SELECT b.nome, SUM(iv.quantidade) as total_vendida " +
                 "FROM itensvenda iv JOIN bebida b ON iv.Itens_codBebida = b.codBebida " +
                 "GROUP BY b.nome ORDER BY total_vendida DESC";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("nome"));
            values.add(rs.getInt("total_vendida"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar bebidas mais vendidas: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}
}
