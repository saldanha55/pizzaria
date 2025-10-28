package com.mycompany.pizzariaweb.modelo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações de acesso a dados (DAO).
 * Define um contrato padrão para todas as classes DAO do projeto.
 * @param <T> O tipo da entidade que o DAO irá manipular.
 */
public interface GenericoDAO<T> {

    // --- CORREÇÃO ---
    // O método 'inserir' agora tem um tipo de retorno 'T' (a entidade).
    // Isso permite que a implementação retorne o objeto com o ID gerado pelo banco.
    // Se o seu método inserir não retorna nada (void), você deve alterar a assinatura aqui para 'public void inserir(T entidade)'.
    // No entanto, retornar a entidade é uma prática recomendada.
    T inserir(T entidade) throws SQLException;

    void alterar(T entidade) throws SQLException;

    void excluir(T entidade) throws SQLException;

    T buscarPorId(Integer id) throws SQLException;

    List<T> listar() throws SQLException;
}
