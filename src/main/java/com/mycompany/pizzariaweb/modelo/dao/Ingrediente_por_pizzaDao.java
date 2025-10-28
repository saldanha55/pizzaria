package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente_por_pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ingrediente_por_pizzaDao implements GenericoDAO<Ingrediente_por_pizza> {

    public Ingrediente_por_pizzaDao() {
    }

    @Override
    public Ingrediente_por_pizza inserir(Ingrediente_por_pizza entidade) throws SQLException {
        String sql = "INSERT INTO ingrediente_por_pizza (Pizza_codPizza, Ingrediente_codIngrediente, quantIngrediente) VALUES (?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, entidade.getPizza_codPizza().getCodPizza());
            stmt.setInt(2, entidade.getIngrediente_codIngrediente().getCodIngrediente());
            stmt.setFloat(3, entidade.getQuantIngrediente());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodigo(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Ingrediente_por_pizza entidade) throws SQLException {
        String sql = "UPDATE ingrediente_por_pizza SET Pizza_codPizza = ?, Ingrediente_codIngrediente = ?, quantIngrediente = ? WHERE codigo = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getPizza_codPizza().getCodPizza());
            stmt.setInt(2, entidade.getIngrediente_codIngrediente().getCodIngrediente());
            stmt.setFloat(3, entidade.getQuantIngrediente());
            stmt.setInt(4, entidade.getCod());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Ingrediente_por_pizza entidade) throws SQLException {
        String sql = "DELETE FROM ingrediente_por_pizza WHERE codigo = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCod());
            stmt.executeUpdate();
        }
    }

    @Override
    public Ingrediente_por_pizza buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM ingrediente_por_pizza WHERE codigo = ?";
        Ingrediente_por_pizza ipp = null;
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ipp = new IngredientePorPizzaRowMapper().mapRow(rs);
                }
            }
        }
        return ipp;
    }

    @Override
    public List<Ingrediente_por_pizza> listar() throws SQLException {
        String sql = "SELECT * FROM ingrediente_por_pizza";
        List<Ingrediente_por_pizza> lista = new ArrayList<>();
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                lista.add(new IngredientePorPizzaRowMapper().mapRow(rs));
            }
        }
        return lista;
    }

    public static class IngredientePorPizzaRowMapper implements RowMapper<Ingrediente_por_pizza> {
        @Override
        public Ingrediente_por_pizza mapRow(ResultSet rs) throws SQLException {
            Ingrediente_por_pizza ipp = new Ingrediente_por_pizza();
            ipp.setCodigo(rs.getInt("codigo"));
            ipp.setQuantIngrediente(rs.getFloat("quantIngrediente"));

            PizzaDao pizzaDao = new PizzaDao();
            Pizza pizza = pizzaDao.buscarPorId(rs.getInt("Pizza_codPizza"));
            ipp.setPizza_codPizza(pizza);

            IngredienteDao ingredienteDao = new IngredienteDao();
            Ingrediente ingrediente = ingredienteDao.buscarPorId(rs.getInt("Ingrediente_codIngrediente"));
            ipp.setIngrediente_codIngrediente(ingrediente);

            return ipp;
        }
    }
}
