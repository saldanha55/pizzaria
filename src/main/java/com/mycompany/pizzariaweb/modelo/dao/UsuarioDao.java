package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.modelo.entidade.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDao implements GenericoDAO<Usuario> {

    @Override
    public Usuario inserir(Usuario entidade) throws SQLException {
        String sql = "INSERT INTO usuario (login, senhaHash, email, Funcionario_codFuncionario) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            String senhaHash = BCrypt.hashpw(entidade.getSenha(), BCrypt.gensalt());
            
            stmt.setString(1, entidade.getLogin());
            stmt.setString(2, senhaHash);
            stmt.setString(3, entidade.getEmail());
            stmt.setInt(4, entidade.getFuncionario().getCodFuncionario());
            
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodUsuario(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Usuario entidade) throws SQLException {
        boolean atualizarSenha = entidade.getSenha() != null && !entidade.getSenha().isEmpty();
        String sql = "UPDATE usuario SET login = ?, email = ?, Funcionario_codFuncionario = ? " +
                     (atualizarSenha ? ", senhaHash = ? " : "") +
                     "WHERE codUsuario = ?";
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, entidade.getLogin());
            stmt.setString(2, entidade.getEmail());
            stmt.setInt(3, entidade.getFuncionario().getCodFuncionario());
            
            if (atualizarSenha) {
                String senhaHash = BCrypt.hashpw(entidade.getSenha(), BCrypt.gensalt());
                stmt.setString(4, senhaHash);
                stmt.setInt(5, entidade.getCodUsuario());
            } else {
                stmt.setInt(4, entidade.getCodUsuario());
            }
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Usuario entidade) throws SQLException {
        String sql = "DELETE FROM usuario WHERE codUsuario = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entidade.getCodUsuario());
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT u.*, f.nome as funcionario_nome FROM usuario u " +
                     "JOIN funcionario f ON u.Funcionario_codFuncionario = f.codFuncionario " +
                     "WHERE u.codUsuario = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioRowMapper().mapRow(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT u.*, f.nome as funcionario_nome FROM usuario u " +
                     "JOIN funcionario f ON u.Funcionario_codFuncionario = f.codFuncionario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new UsuarioRowMapper().mapRow(rs));
            }
        }
        return usuarios;
    }

    public Usuario buscarPorLogin(String login) throws SQLException {
        String sql = "SELECT u.*, f.nome as funcionario_nome FROM usuario u " +
                     "JOIN funcionario f ON u.Funcionario_codFuncionario = f.codFuncionario " +
                     "WHERE u.login = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioRowMapper().mapRow(rs);
                }
            }
        }
        return usuario;
    }

    public Usuario buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT u.*, f.nome as funcionario_nome FROM usuario u " +
                     "JOIN funcionario f ON u.Funcionario_codFuncionario = f.codFuncionario " +
                     "WHERE u.email = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioRowMapper().mapRow(rs);
                }
            }
        }
        return usuario;
    }

    public void salvarToken(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET token_reset_senha = ? WHERE codUsuario = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getTokenResetSenha());
            stmt.setInt(2, usuario.getCodUsuario());
            stmt.executeUpdate();
        }
    }

    public Usuario buscarPorToken(String token) throws SQLException {
        String sql = "SELECT u.*, f.nome as funcionario_nome FROM usuario u " +
                     "JOIN funcionario f ON u.Funcionario_codFuncionario = f.codFuncionario " +
                     "WHERE u.token_reset_senha = ?";
        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new UsuarioRowMapper().mapRow(rs);
                }
            }
        }
        return usuario;
    }

    public void alterarSenhaComToken(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET senhaHash = ?, token_reset_senha = NULL WHERE codUsuario = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            String senhaHash = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            stmt.setString(1, senhaHash);
            stmt.setInt(2, usuario.getCodUsuario());
            stmt.executeUpdate();
        }
    }
    
    public static class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setCodUsuario(rs.getInt("codUsuario"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenhaHash(rs.getString("senhaHash"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTokenResetSenha(rs.getString("token_reset_senha"));
            
            Funcionario funcionario = new Funcionario();
            funcionario.setCodFuncionario(rs.getInt("Funcionario_codFuncionario"));
            funcionario.setNome(rs.getString("funcionario_nome"));
            
            usuario.setFuncionario(funcionario);

            return usuario;
        }
    }
}