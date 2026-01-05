package model;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String nome;
    private String sobrenome;
    private double saldo;

    private boolean bloqueada;

    private double saqueHoje;
    private double transferenciaHoje;

    private static final double LIMITE_SAQUE_DIARIO = 3000;
    private static final double LIMITE_TRANSFERENCIA_DIARIO = 5000;

    private List<String> historico;

    // ================= CONSTRUTOR =================

    public Conta(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.saldo = 0;
        this.bloqueada = false;
        this.saqueHoje = 0;
        this.transferenciaHoje = 0;
        this.historico = new ArrayList<>();

        historico.add("Conta criada para " + getNomeCompleto());
    }
    // ================= GETTERS =================

    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
    public double getSaldo() {
        registrar("Consulta de saldo");
        return saldo;
    }
    public boolean isBloqueada() {
        return bloqueada;
    }

    // ================= OPERAÇÕES =================

    public boolean depositar(double valor) {
        if (bloqueada || valor <= 0) {
            registrar("Depósito negado: R$ " + valor);
            return false;
        }
        saldo += valor;
        registrar("Depósito realizado: R$ " + valor);
        return true;
    }
    public boolean sacar(double valor) {
        if (bloqueada || valor <= 0 || valor > saldo) {
            registrar("Saque negado: R$ " + valor);
            return false;
        }
        if (saqueHoje + valor > LIMITE_SAQUE_DIARIO) {
            bloquear("Limite diário de saque excedido");
            return false;
        }
        saqueHoje += valor;
        saldo -= valor;
        registrar("Saque realizado: R$ " + valor);
        return true;
    }
    public boolean transferir(Conta destino, double valor) {
        if (bloqueada || valor <= 0 || valor > saldo || destino == null) {
            registrar("Transferência negada: R$ " + valor);
            return false;
        }
        if (transferenciaHoje + valor > LIMITE_TRANSFERENCIA_DIARIO) {
            bloquear("Limite diário de transferência excedido");
            return false;
        }
        transferenciaHoje += valor;
        saldo -= valor;
        destino.saldo += valor;

        registrar("Transferência enviada: R$ " + valor + " para " + destino.getNomeCompleto());
        destino.registrar("Transferência recebida: R$ " + valor + " de " + getNomeCompleto());

        return true;
    }

    // ================= HISTÓRICO =================

    public void exibirHistorico() {
        System.out.println("===== HISTÓRICO =====");
        if (historico.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
            return;
        }
        historico.forEach(System.out::println);
    }
    private void registrar(String evento) {
        historico.add(evento);
    }

    // ================= BLOQUEIO / LIMITE =================

    private void bloquear(String motivo) {
        bloqueada = true;
        registrar("CONTA BLOQUEADA: " + motivo);
    }
    public void resetarLimitesDiarios() {
        saqueHoje = 0;
        transferenciaHoje = 0;
        registrar("Limites diários resetados");
    }
}
