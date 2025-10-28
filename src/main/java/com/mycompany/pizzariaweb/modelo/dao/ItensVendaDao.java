package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.ItensVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItensVendaDao implements GenericoDAO<ItensVenda> {

    public ItensVendaDao() {
    }

    @Override
    public ItensVenda inserir(ItensVenda entidade) throws SQLException {
        String sql = "INSERT INTO itensvenda (Itens_codVenda, Itens_codPizza, Itens_codBebida, quantidade, valor) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, entidade.getItens_codVenda().getCodPedido());

            if (entidade.getItens_codPizza() != null) {
                stmt.setInt(2, entidade.getItens_codPizza().getCodPizza());
                stmt.setNull(3, java.sql.Types.INTEGER);
            } else if (entidade.getItens_codBebida() != null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
                stmt.setInt(3, entidade.getItens_codBebida().getCodBebida());
            } else {
                throw new SQLException("O item da venda deve ter uma pizza ou uma bebida.");
            }

            stmt.setInt(4, entidade.getQuantidade());
            stmt.setDouble(5, entidade.getValor());
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodItensVenda(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    public List<ItensVenda> listarPorVenda(int vendaId) throws SQLException {
        String sql = "SELECT * FROM itensvenda WHERE Itens_codVenda = ?";
        List<ItensVenda> itens = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, vendaId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    itens.add(new ItensVendaRowMapper().mapRow(rs));
                }
            }
        }
        return itens;
    }

    @Override
    public void alterar(ItensVenda entidade) throws SQLException {
        // A alteração de um item de venda geralmente não é uma prática comum.
        // Normalmente, o item é removido e adicionado novamente com os novos valores.
        // Deixado em branco intencionalmente.
    }

    @Override
    public void excluir(ItensVenda entidade) throws SQLException {
        String sql = "DELETE FROM itensvenda WHERE codItensVenda = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodItensVenda());
            stmt.executeUpdate();
        }
    }

    @Override
    public ItensVenda buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM itensvenda WHERE codItensVenda = ?";
        ItensVenda item = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = new ItensVendaRowMapper().mapRow(rs);
                }
            }
        }
        return item;
    }
    
    @Override
    public List<ItensVenda> listar() throws SQLException {
        String sql = "SELECT * FROM itensvenda";
        List<ItensVenda> itens = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                itens.add(new ItensVendaRowMapper().mapRow(rs));
            }
        }
        return itens;
    }
    
    public static class ItensVendaRowMapper implements RowMapper<ItensVenda> {
        @Override
        public ItensVenda mapRow(ResultSet rs) throws SQLException {
            ItensVenda item = new ItensVenda();
            item.setCodItensVenda(rs.getInt("codItensVenda"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.setValor(rs.getDouble("valor"));

            VendaDao vendaDao = new VendaDao();
            item.setItens_codVenda(vendaDao.buscarPorId(rs.getInt("Itens_codVenda")));

            int pizzaId = rs.getInt("Itens_codPizza");
            if (!rs.wasNull()) {
                PizzaDao pizzaDao = new PizzaDao();
                item.setItens_codPizza(pizzaDao.buscarPorId(pizzaId));
            }

            int bebidaId = rs.getInt("Itens_codBebida");
            if (!rs.wasNull()) {
                BebidaDao bebidaDao = new BebidaDao();
                item.setItens_codBebida(bebidaDao.buscarPorId(bebidaId));
            }

            return item;
        }
    }
}
