package model;

import java.time.LocalDateTime;

public class Transacao {

    private TipoTransacao tipo;
    private double valor;
    private String descricao;
    private LocalDateTime dataHora;

    public Transacao(TipoTransacao tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }
    public TipoTransacao getTipo() {
        return tipo;
    }
    public double getValor() {
        return valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    @Override
    public String toString() {
        return dataHora + " | " + tipo + " | R$ " + valor + " | " + descricao;
    }
}
