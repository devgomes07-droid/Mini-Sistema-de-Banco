package model;

import java.util.UUID;

public class Usuario {

    // ===== ATRIBUTOS =====

    private final String id;        // Identificador único
    private String nome;
    private String login;
    private String senha;

    // ===== CONSTRUTOR =====

    public Usuario(String nome, String login, String senha) {
        this.id = UUID.randomUUID().toString();        // gera ID único automático
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    // ===== GETTERS =====

    public String getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getLogin() {
        return login;
    }

    // ⚠️ NÃO CRIAMOS getSenha()
    // Segurança básica: nunca exponha senha

    // ===== SETTERS =====

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ===== MÉTODOS DE NEGÓCIO =====

    public boolean validarSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }
    // ===== toString =====
    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}