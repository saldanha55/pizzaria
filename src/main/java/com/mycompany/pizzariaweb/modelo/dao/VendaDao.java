package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import com.mycompany.pizzariaweb.servico.ConverteData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendaDao implements GenericoDAO<Venda> {

    public VendaDao() {
    }

    @Override
    public Venda inserir(Venda entidade) throws SQLException {
        String sql = "INSERT INTO venda (data, tipoPagamento, desconto, comissao, Cliente_codCliente, Funcionario_codFuncionario) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, ConverteData.convertDateToSQL(entidade.getData()));
            stmt.setString(2, entidade.getTipoPagamento());
            stmt.setDouble(3, entidade.getDesconto());
            stmt.setDouble(4, entidade.getComissao());
            stmt.setInt(5, entidade.getCliente().getCodCliente());
            stmt.setInt(6, entidade.getFuncionario().getCodFuncionario());

            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entidade.setCodPedido(rs.getInt(1));
                }
            }
        }
        return entidade;
    }

    @Override
    public void alterar(Venda entidade) throws SQLException {
        String sql = "UPDATE venda SET data = ?, tipoPagamento = ?, desconto = ?, comissao = ?, " +
                     "Cliente_codCliente = ?, Funcionario_codFuncionario = ? WHERE codPedido = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setDate(1, ConverteData.convertDateToSQL(entidade.getData()));
            stmt.setString(2, entidade.getTipoPagamento());
            stmt.setDouble(3, entidade.getDesconto());
            stmt.setDouble(4, entidade.getComissao());
            stmt.setInt(5, entidade.getCliente().getCodCliente());
            stmt.setInt(6, entidade.getFuncionario().getCodFuncionario());
            stmt.setInt(7, entidade.getCodPedido());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void excluir(Venda entidade) throws SQLException {
        String sql = "DELETE FROM venda WHERE codPedido = ?";
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, entidade.getCodPedido());
            stmt.executeUpdate();
        }
    }
    
    @Override
    public Venda buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM venda WHERE codPedido = ?";
        Venda venda = null;
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venda = new VendaRowMapper().mapRow(rs);
                }
            }
        }
        return venda;
    }

    @Override
    public List<Venda> listar() throws SQLException {
        String sql = "SELECT * FROM venda";
        List<Venda> vendas = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                vendas.add(new VendaRowMapper().mapRow(rs));
            }
        }
        return vendas;
    }
    
    public static class VendaRowMapper implements RowMapper<Venda> {
        @Override
        public Venda mapRow(ResultSet rs) throws SQLException {
            Venda venda = new Venda();
            venda.setCodPedido(rs.getInt("codPedido"));
            venda.setData(rs.getDate("data"));
            venda.setTipoPagamento(rs.getString("tipoPagamento"));
            venda.setDesconto(rs.getDouble("desconto"));
            venda.setComissao(rs.getDouble("comissao"));

            ClienteDao clienteDao = new ClienteDao();
            venda.setCliente(clienteDao.buscarPorId(rs.getInt("Cliente_codCliente")));

            FuncionarioDao funcionarioDao = new FuncionarioDao();
            venda.setFuncionario(funcionarioDao.buscarPorId(rs.getInt("Funcionario_codFuncionario")));

            return venda;
        }
    }
    
    public RelatorioDTO getVendasPorDiaDaSemana() {
    String sql = "SELECT CASE DAYOFWEEK(data) " +
                 "WHEN 1 THEN 'Domingo' WHEN 2 THEN 'Segunda' WHEN 3 THEN 'Terça' " +
                 "WHEN 4 THEN 'Quarta' WHEN 5 THEN 'Quinta' WHEN 6 THEN 'Sexta' " +
                 "WHEN 7 THEN 'Sábado' END as dia_semana, " +
                 "COUNT(codPedido) as total " +
                 "FROM venda GROUP BY dia_semana, DAYOFWEEK(data) ORDER BY DAYOFWEEK(data)";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("dia_semana"));
            values.add(rs.getInt("total"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar vendas por dia da semana: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}

public RelatorioDTO getFaturamentoMensal() {
    String sql = "SELECT " +
                 "  DATE_FORMAT(v.data, '%m/%Y') as mes_formatado, " +
                 "  YEAR(v.data) as ano, " +
                 "  MONTH(v.data) as mes_num, " +
                 "  SUM(iv.valor * iv.quantidade) as faturamento " +
                 "FROM venda v " +
                 "JOIN itensvenda iv ON v.codPedido = iv.Itens_codVenda " +
                 "GROUP BY ano, mes_num, mes_formatado " +
                 "ORDER BY ano, mes_num";

    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    System.out.println("--- VendaDao.getFaturamentoMensal --- EXECUTANDO QUERY:"); // DEBUG
    System.out.println(sql); // DEBUG - Mostra a query exata

    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        System.out.println("--- VendaDao.getFaturamentoMensal --- RESULTADOS:"); // DEBUG
        int rowCount = 0; // DEBUG - Contador de linhas

        while (rs.next()) {
            rowCount++; // DEBUG
            String mesFormatado = rs.getString("mes_formatado");
            double faturamento = rs.getDouble("faturamento");

            // DEBUG - Imprime cada linha que o Java está a ler
            System.out.println("Linha " + rowCount + ": Mes = " + mesFormatado + ", Faturamento = " + faturamento);

            labels.add(mesFormatado);
            values.add(faturamento);
        }

        // DEBUG - Mostra quantas linhas foram processadas
        System.out.println("--- VendaDao.getFaturamentoMensal --- Total de linhas processadas: " + rowCount);

    } catch (SQLException e) {
        System.err.println("--- VendaDao.getFaturamentoMensal --- ERRO SQL:"); // DEBUG
        e.printStackTrace(); // DEBUG - Imprime o erro detalhado
        throw new RuntimeException("Erro ao buscar faturamento mensal: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}

public RelatorioDTO getVendasPorTipoPagamento() {
    String sql = "SELECT tipoPagamento, COUNT(codPedido) as total FROM venda GROUP BY tipoPagamento ORDER BY total DESC";
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("tipoPagamento"));
            values.add(rs.getInt("total"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar vendas por tipo de pagamento: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}

public RelatorioDTO getVendasPorBairro() {
    String sql = "SELECT c.bairro, COUNT(v.codPedido) as total " +
                 "FROM venda v JOIN cliente c ON v.Cliente_codCliente = c.codCliente " +
                 "GROUP BY c.bairro ORDER BY total DESC LIMIT 10"; // Top 10 bairros
    List<String> labels = new ArrayList<>();
    List<Object> values = new ArrayList<>();

    try (java.sql.Connection conn = ConnectionFactory.getInstance().getConnection();
         java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
         java.sql.ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            labels.add(rs.getString("bairro"));
            values.add(rs.getInt("total"));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar vendas por bairro: " + e.getMessage(), e);
    }
    return new RelatorioDTO(labels, values);
}
}
