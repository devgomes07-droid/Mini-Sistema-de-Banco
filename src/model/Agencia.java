package model;

import java.util.ArrayList;
import java.util.List;

public class Agencia {

    // === ATRIBUTOS ===
    private String numero;
    private String nome;
    private List<Conta> contas;

    // === CONSTRUTOR ===
    public Agencia(String numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    // === MÉTODOS ===

    public void adicionarConta(Conta conta) {
        if (conta != null && !contas.contains(conta)) {
            contas.add(conta);
            System.out.println("Conta de " + conta.getNomeCompleto() + " adicionada à agência " + nome);
        } else {
            System.out.println("Conta inválida ou já existente");
        }
    }
    public void listarContas() {
        System.out.println("=== Contas da Agência " + nome + " ===");
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada");
            return;
        }
        for (Conta c : contas) {
            System.out.println(c.getNomeCompleto() + " | Saldo: R$ " + c.getSaldo());
        }
    }
    public Conta buscarConta(String nomeCompleto) {
        for (Conta c : contas) {
            if (c.getNomeCompleto().equalsIgnoreCase(nomeCompleto)) {
                return c;
            }
        }
        return null;
    }

    // === GETTERS ===
    public String getNumero() { return numero; }
    public String getNome() { return nome; }
    public List<Conta> getContas() {
        return new ArrayList<>(contas);
    }
}

