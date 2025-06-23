/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.modelo.dao;

import com.mycompany.pizzariaweb.modelo.entidade.ItensVenda;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author tulio
 */
public class ItensVendaDao extends GenericoDAO<ItensVenda> {

    public ItensVenda buscarPorId(int id) {// modificando igual a getListaItensVenda
        String select = "select * from itensvenda where Itens_codVenda=?";
        return buscarPorId(select, new ItensVendaDao.ItensVendaRowMapper(), id);
    }

    public void salvar(ItensVenda i) {
        String insert = "insert into itensvenda (Itens_codVenda, Itens_codPizza, quantidade, valor) values (?,?,?,?)";
        save(insert, i.getObjItens_codVenda().getCodPedido(), i.getObjItens_codPizza().getCodPizza(), i.getQuantidade(), i.getValor());
    }

    public void excluir(ItensVenda i) {
        String delete = "delete from itensvenda where codItensVenda=?";
        save(delete, i.getCodItensVenda());
    }

    public List<ItensVenda> buscarTodosPorId(int id) {
        String sql = "select * from itensvenda where Itens_codVenda = ?";
        List<ItensVenda> lista = buscarTodos(sql, new ItensVendaRowMapper(), id);
        System.out.println("LISTA DE ITENSVENDA {");
        for(ItensVenda item : lista)
            System.out.println(item.toString());
        System.out.println("}");
        return lista;
    }

    public static class ItensVendaRowMapper implements RowMapper<ItensVenda> {

        PizzaDao pizzaDao = new PizzaDao();
        VendaDao vendaDao = new VendaDao();

        @Override
        public ItensVenda mapRow(ResultSet rs) throws SQLException {
            ItensVenda itensVenda = new ItensVenda();
            itensVenda.setCodItensVenda(rs.getInt("codItensVenda"));
            itensVenda.setObjItens_codPizza(pizzaDao.buscarPorId(rs.getInt("Itens_codPizza")));
            itensVenda.setObjItens_codVenda(vendaDao.buscarPorId(rs.getInt("Itens_codVenda")));
            itensVenda.setQuantidade(rs.getInt("quantidade"));
            itensVenda.setValor(rs.getDouble("valor"));
            return itensVenda;
        }

    }

    /*  
public List<ItensVenda> getListaItensVenda(Integer id) {
        String sql = "select * from itensvenda where idvenda=?";
        List<ItensVenda> lista = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItensVenda obj = new ItensVenda();
                System.out.println("IDVENDA=" + rs.getInt("idvenda"));
                // obj.getObjVenda().setIdVenda(rs.getInt("idvenda"));
                obj.setIdItensVenda(rs.getInt("iditensvenda"));
                obj.setObjProduto(produtoDao.buscarPorId(rs.getInt("idProduto")));
                obj.setQuantidadeProduto(rs.getInt("quantidade"));
                obj.getObjProduto().setValor(rs.getDouble("valor"));
                lista.add(obj);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String incluir(ItensVenda objItensVenda) {
        String mensagem = "";

        String sql = "insert into itensvenda (idproduto,idvenda,quantidade,valor) values (?,?,?,?)";
        try (Connection con = ConnectionFactory.getInstance().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, objItensVenda.getObjProduto().getIdProduto());
            stmt.setInt(2, objItensVenda.getObjVenda().getIdVenda());
            stmt.setInt(3, objItensVenda.getQuantidadeProduto());
            stmt.setDouble(4, objItensVenda.getObjProduto().getValor());

            if (stmt.executeUpdate() > 0) {
                mensagem = "Item de venda cadastrado com sucesso!";
            } else {
                mensagem = "Item de venda não cadastrado!";
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
            //Este método é chamado para imprimir o rastreamento da pilha de execução da exceção. Isso inclui a sequência de chamadas de métodos que levaram à ocorrência da exceção. É uma maneira útil de depurar o código, pois fornece informações detalhadas sobre onde e por que o erro ocorreu.
        }
        return mensagem;

    }

    public String remover(ItensVenda objItensVenda) {
        String sql = "delete from itensvenda where iditensvenda=?";
        String mensagem = "";
        try (Connection con = ConnectionFactory.getInstance().getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, objItensVenda.getIdItensVenda());
            if (stmt.executeUpdate() > 0) {
                mensagem = "Item de venda removido com sucesso!";

            } else {
                mensagem = "Item de venda não removido!";

            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mensagem;

    }
     */
}
