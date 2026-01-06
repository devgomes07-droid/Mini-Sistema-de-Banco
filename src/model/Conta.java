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

    private static final double LIMITE_SAQUE_DIARIO = 3000.0;
    private static final double LIMITE_TRANSFERENCIA_DIARIO = 5000.0;

    private List<String> historico;

    public Conta(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.saldo = 0.0;
        this.bloqueada = false;
        this.saqueHoje = 0.0;
        this.transferenciaHoje = 0.0;
        this.historico = new ArrayList<>();

        historico.add("Conta criada para " + getNomeCompleto());
    }
    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
    public double getSaldo() {
        return saldo;
    }
    public boolean isBloqueada() {
        return bloqueada;
    }

    /* ================= OPERAÇÕES ================= */

    public boolean depositar(double valor) {
        if (bloqueada || valor <= 0) {
            historico.add("Depósito inválido: R$ " + valor);
            return false;
        }
        saldo += valor;
        historico.add("Depósito: R$ " + valor);
        return true;
    }
    public boolean sacar(double valor) {
        if (bloqueada) {
            historico.add("Saque negado: conta bloqueada");
            return false;
        }
        if (valor <= 0) {
            historico.add("Saque inválido: R$ " + valor);
            return false;
        }
        if (valor > saldo) {
            historico.add("Saque negado: saldo insuficiente");
            return false;
        }
        if (saqueHoje + valor > LIMITE_SAQUE_DIARIO) {
            bloquearConta("Limite diário de saque excedido");
            return false;
        }
        saldo -= valor;
        saqueHoje += valor;
        historico.add("Saque: R$ " + valor);
        return true;
    }
    public boolean transferir(Conta destino, double valor) {
        if (bloqueada) {
            historico.add("Transferência negada: conta bloqueada");
            return false;
        }
        if (valor <= 0) {
            historico.add("Transferência inválida: R$ " + valor);
            return false;
        }
        if (valor > saldo) {
            historico.add("Transferência negada: saldo insuficiente");
            return false;
        }
        if (transferenciaHoje + valor > LIMITE_TRANSFERENCIA_DIARIO) {
            bloquearConta("Limite diário de transferência excedido");
            return false;
        }
        saldo -= valor;
        transferenciaHoje += valor;
        destino.saldo += valor;

        historico.add("Transferência enviada: R$ " + valor + " para " + destino.getNomeCompleto());
        destino.historico.add("Transferência recebida: R$ " + valor + " de " + getNomeCompleto());

        return true;
    }

    /* ================= AUXILIARES ================= */

    private void bloquearConta(String motivo) {
        bloqueada = true;
        historico.add("CONTA BLOQUEADA: " + motivo);
    }
    public void resetarLimitesDiarios() {
        saqueHoje = 0.0;
        transferenciaHoje = 0.0;
        historico.add("Limites diários resetados");
    }
    public void verHistorico() {
        System.out.println("===== HISTÓRICO =====");
        for (String h : historico) {
            System.out.println(h);
        }
    }
}