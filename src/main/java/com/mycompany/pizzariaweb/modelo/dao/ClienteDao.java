package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements GenericoDAO<Cliente> {

    public ClienteDao() {
    }

    @Override
    public Cliente inserir(Cliente entidade) throws SQLException {
        String sql = "INSERT INTO cliente (nome, telefone, cpf, bairro, rua, numero, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getCpf());
            stmt.setString(4, entidade.getBairro());
            stmt.setString(5, entidade.getRua());
            stmt.setString(6, entidade.getNumero());
            stmt.setString(7, entidade.getEmail());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodCliente(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Cliente entidade) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, telefone = ?, cpf = ?, bairro = ?, rua = ?, numero = ?, email = ? WHERE codCliente = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getTelefone());
            stmt.setString(3, entidade.getCpf());
            stmt.setString(4, entidade.getBairro());
            stmt.setString(5, entidade.getRua());
            stmt.setString(6, entidade.getNumero());
            stmt.setString(7, entidade.getEmail());
            stmt.setInt(8, entidade.getCodCliente());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Cliente entidade) throws SQLException {
        String sql = "DELETE FROM cliente WHERE codCliente = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodCliente());
            stmt.executeUpdate();
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE codCliente = ?";
        Cliente cliente = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteRowMapper().mapRow(rs);
                }
            }
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                clientes.add(new ClienteRowMapper().mapRow(rs));
            }
        }
        return clientes;
    }

    public static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setCodCliente(rs.getInt("codCliente"));
            cliente.setNome(rs.getString("nome"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setRua(rs.getString("rua"));
            cliente.setNumero(rs.getString("numero"));
            cliente.setEmail(rs.getString("email"));
            return cliente;
        }
    }
    public RelatorioDTO getTop5Clientes() {
    String sql = "SELECT c.nome, SUM(iv.valor * iv.quantidade) as total_gasto " +
                 "FROM venda v " +
                 "JOIN cliente c ON v.Cliente_codCliente = c.codCliente " +
                 "JOIN itensvenda iv ON v.codPedido = iv.Itens_codVenda " +
                 "GROUP BY c.nome ORDER BY total_gasto DESC";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("nome"));
            values.add(rs.getDouble("total_gasto"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar top 5 clientes: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}
    public RelatorioDTO getRelatorioClientesPorBairro() {
        String sql = "SELECT bairro, COUNT(*) as total FROM cliente GROUP BY bairro ORDER BY total DESC";
        
        // Note que List<Object> é usado para os valores, conforme solicitado.
        List<String> labels = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
             java.sql.ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                labels.add(rs.getString("bairro"));
                values.add(rs.getInt("total")); // Adiciona um Integer (que é um Object)
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar relatório de clientes por bairro: " + e.getMessage(), e);
        }
        return new RelatorioDTO(labels, values);
    }
}
