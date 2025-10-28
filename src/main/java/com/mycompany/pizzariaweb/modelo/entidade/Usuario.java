package com.mycompany.pizzariaweb.modelo.entidade;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer codUsuario;
    private String login;
    private String senha; // Usado apenas para cadastro/alteração
    private String senhaHash; // Armazenado no banco
    private String email;
    private Funcionario funcionario;
    private String tokenResetSenha; // <-- CAMPO ADICIONADO

    // Construtores
    public Usuario() {
    }

    // Getters e Setters (incluindo para o novo campo)

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    // --- GETTER E SETTER PARA O TOKEN ---
    public String getTokenResetSenha() {
        return tokenResetSenha;
    }

    public void setTokenResetSenha(String tokenResetSenha) {
        this.tokenResetSenha = tokenResetSenha;
    }
}