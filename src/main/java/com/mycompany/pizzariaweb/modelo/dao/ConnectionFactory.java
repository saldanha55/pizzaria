package com.mycompany.pizzariaweb.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    // Variáveis para armazenar as credenciais lidas do ambiente
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private final String dbDriver = "com.mysql.cj.jdbc.Driver"; // Ou "org.postgresql.Driver" se usar PostgreSQL

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        // Lê as variáveis de ambiente fornecidas pelo Render (ou usa defaults locais)
        // *** IMPORTANTE: Os nomes DATABASE_URL, DB_USER, DB_PASSWORD são exemplos.
        // *** Verifique os nomes exatos que o Render usa! ***
        String databaseUrlEnv = System.getenv("DATABASE_URL"); // Render geralmente fornece uma URL completa
        String dbHostEnv = System.getenv("DB_HOST");
        String dbPortEnv = System.getenv("DB_PORT"); // Geralmente 5432 para PostgreSQL, 3306 para MySQL
        String dbNameEnv = System.getenv("DB_NAME");

        dbUser = System.getenv("DB_USER");
        dbPassword = System.getenv("DB_PASSWORD");

        // Lógica para construir a URL de conexão
        if (databaseUrlEnv != null && !databaseUrlEnv.isEmpty()) {
             // Se o Render fornecer a URL completa (comum para Heroku/Render com PostgreSQL)
             // Adapte conforme necessário para o formato JDBC (pode precisar remover "postgres://" e adicionar "jdbc:")
             // Exemplo de adaptação para PostgreSQL:
             // this.dbUrl = databaseUrlEnv.replace("postgres://", "jdbc:postgresql://");

             // Exemplo direto se a URL já estiver no formato JDBC (menos comum)
             this.dbUrl = databaseUrlEnv;
             LOGGER.log(Level.INFO, "Usando DATABASE_URL do ambiente.");

        } else if (dbHostEnv != null && dbPortEnv != null && dbNameEnv != null) {
            // Se o Render fornecer partes separadas (Host, Porta, Nome) - Mais comum para MySQL/instâncias separadas
            // Adapte o prefixo JDBC conforme o banco: "jdbc:mysql://" ou "jdbc:postgresql://"
             this.dbUrl = "jdbc:mysql://" + dbHostEnv + ":" + dbPortEnv + "/" + dbNameEnv + "?useSSL=false"; // Exemplo MySQL
            // this.dbUrl = "jdbc:postgresql://" + dbHostEnv + ":" + dbPortEnv + "/" + dbNameEnv; // Exemplo PostgreSQL
            LOGGER.log(Level.INFO, "Construindo URL JDBC a partir de DB_HOST, DB_PORT, DB_NAME.");

        } else {
            // Fallback para configuração local se as variáveis de ambiente não estiverem definidas
            LOGGER.log(Level.WARNING, "Variáveis de ambiente do banco de dados não encontradas. Usando configuração local.");
            this.dbUrl = "jdbc:mysql://localhost:3307/pizzaria?useSSL=false"; // Seu localhost
            // Não defina usuário/senha aqui por segurança, mas poderia para teste local:
            // this.dbUser = "root";
            // this.dbPassword = "1234";
        }

         // Validação básica
         if (this.dbUrl == null || this.dbUser == null || this.dbPassword == null) {
             LOGGER.log(Level.SEVERE, "Faltam credenciais do banco de dados (URL, Usuário ou Senha). Verifique as variáveis de ambiente!");
             // Você pode querer lançar uma RuntimeException aqui para impedir a inicialização
             // throw new RuntimeException("Credenciais do banco de dados ausentes!");
         } else {
              LOGGER.log(Level.INFO, "URL do Banco: {0}", this.dbUrl);
              LOGGER.log(Level.INFO, "Usuário do Banco: {0}", this.dbUser);
              // Não logue a senha em produção!
         }


        // Carregar o Driver JDBC
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
             LOGGER.log(Level.SEVERE, "Driver JDBC não encontrado: " + dbDriver, e);
            throw new RuntimeException("Driver do banco de dados não encontrado: " + dbDriver, e);
        }
    }

    public static synchronized ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
         if (this.dbUrl == null || this.dbUser == null || this.dbPassword == null) {
             throw new SQLException("Configuração do banco de dados incompleta. Verifique as variáveis de ambiente.");
         }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    // Método getPreparedStatement removido pois não é usado pela sua implementação atual do GenericoDAO
    /*
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        Connection con = getConnection();
        // Verifique se você realmente precisa de RETURN_GENERATED_KEYS em todos os lugares
        return con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }
    */
}