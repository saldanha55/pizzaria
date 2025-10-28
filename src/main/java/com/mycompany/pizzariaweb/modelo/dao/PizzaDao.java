package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PizzaDao implements GenericoDAO<Pizza> {

    // --- CORREÇÃO ---
    // O construtor agora está vazio. A conexão não é mais um membro da classe.
    public PizzaDao() {
    }

    @Override
    public Pizza inserir(Pizza entidade) throws SQLException {
        String sql = "INSERT INTO pizza (nome, precoBase, tamanho, Pizza_borda) VALUES (?, ?, ?, ?)";
        
        // --- CORREÇÃO ---
        // A conexão é obtida e fechada para esta operação específica.
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPrecoBase());
            stmt.setString(3, entidade.getTamanho());
            stmt.setInt(4, entidade.getBorda().getCodBorda());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodPizza(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Pizza entidade) throws SQLException {
        String sql = "UPDATE pizza SET nome = ?, precoBase = ?, tamanho = ?, Pizza_borda = ? WHERE codPizza = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPrecoBase());
            stmt.setString(3, entidade.getTamanho());
            stmt.setInt(4, entidade.getBorda().getCodBorda());
            stmt.setInt(5, entidade.getCodPizza());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Pizza entidade) throws SQLException {
        String sql = "DELETE FROM pizza WHERE codPizza = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodPizza());
            stmt.executeUpdate();
        }
    }

    @Override
    public Pizza buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM pizza WHERE codPizza = ?";
        Pizza pizza = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pizza = new PizzaRowMapper().mapRow(rs);
                }
            }
        }
        return pizza;
    }

    @Override
    public List<Pizza> listar() throws SQLException {
        String sql = "SELECT * FROM pizza";
        List<Pizza> pizzas = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pizzas.add(new PizzaRowMapper().mapRow(rs));
            }
        }
        return pizzas;
    }

    public static class PizzaRowMapper implements RowMapper<Pizza> {
        @Override
        public Pizza mapRow(ResultSet rs) throws SQLException {
            Pizza pizza = new Pizza();
            pizza.setCodPizza(rs.getInt("codPizza"));
            pizza.setNome(rs.getString("nome"));
            pizza.setPrecoBase(rs.getDouble("precoBase"));
            pizza.setTamanho(rs.getString("tamanho"));

            // Busca o objeto RecheioBorda relacionado
            // Este DAO também deve ser ajustado para não manter a conexão como membro.
            RecheioBordaDao recheioBordaDao = new RecheioBordaDao();
            RecheioBorda borda = recheioBordaDao.buscarPorId(rs.getInt("Pizza_borda"));
            pizza.setBorda(borda);
            
            return pizza;
        }
    }
    public RelatorioDTO getPizzasMaisVendidas() {
    String sql = "SELECT p.nome, SUM(iv.quantidade) as total_vendida " +
                 "FROM itensvenda iv JOIN pizza p ON iv.Itens_codPizza = p.codPizza " +
                 "GROUP BY p.nome ORDER BY total_vendida DESC";
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
        throw new RuntimeException("Erro ao buscar pizzas mais vendidas: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}

public RelatorioDTO getBordasMaisVendidas() {
    String sql = "SELECT rb.sabor, COUNT(p.Pizza_borda) as total " +
                 "FROM pizza p JOIN recheio_borda rb ON p.Pizza_borda = rb.codBorda " +
                 "JOIN itensvenda iv ON p.codPizza = iv.Itens_codPizza " +
                 "GROUP BY rb.sabor ORDER BY total DESC";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("sabor"));
            values.add(rs.getInt("total"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar bordas mais vendidas: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}

public RelatorioDTO getVendasPorTamanhoPizza() {
    String sql = "SELECT p.tamanho, SUM(iv.quantidade) as total " +
                 "FROM itensvenda iv JOIN pizza p ON iv.Itens_codPizza = p.codPizza " +
                 "GROUP BY p.tamanho ORDER BY total DESC";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("tamanho"));
            values.add(rs.getInt("total"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar vendas por tamanho de pizza: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}
}
