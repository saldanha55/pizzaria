package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecheioBordaDao implements GenericoDAO<RecheioBorda> {

    public RecheioBordaDao() {
    }

    @Override
    public RecheioBorda inserir(RecheioBorda entidade) throws SQLException {
        String sql = "INSERT INTO recheio_borda (sabor, preco) VALUES (?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getSabor());
            stmt.setDouble(2, entidade.getPreco());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodBorda(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(RecheioBorda entidade) throws SQLException {
        String sql = "UPDATE recheio_borda SET sabor = ?, preco = ? WHERE codBorda = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getSabor());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setInt(3, entidade.getCodBorda());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(RecheioBorda entidade) throws SQLException {
        String sql = "DELETE FROM recheio_borda WHERE codBorda = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodBorda());
            stmt.executeUpdate();
        }
    }

    @Override
    public RecheioBorda buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM recheio_borda WHERE codBorda = ?";
        RecheioBorda recheioBorda = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    recheioBorda = new RecheioBordaRowMapper().mapRow(rs);
                }
            }
        }
        return recheioBorda;
    }

    @Override
    public List<RecheioBorda> listar() throws SQLException {
        String sql = "SELECT * FROM recheio_borda";
        List<RecheioBorda> recheios = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                recheios.add(new RecheioBordaRowMapper().mapRow(rs));
            }
        }
        return recheios;
    }

    public static class RecheioBordaRowMapper implements RowMapper<RecheioBorda> {
        @Override
        public RecheioBorda mapRow(ResultSet rs) throws SQLException {
            RecheioBorda recheioBorda = new RecheioBorda();
            recheioBorda.setCodBorda(rs.getInt("codBorda"));
            recheioBorda.setSabor(rs.getString("sabor"));
            recheioBorda.setPreco(rs.getDouble("preco"));
            return recheioBorda;
        }
    }
}
