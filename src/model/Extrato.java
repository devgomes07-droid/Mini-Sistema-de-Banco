package model;

import java.util.ArrayList;
import java.util.List;

public class Extrato {

    // === ATRIBUTO ===

    private List<Transacao> transacoes;

    // === CONSTRUTOR ===

    public Extrato() {
        this.transacoes = new ArrayList<>();
    }

    // === MÉTODOS ===

//adiciona uma transação ao extrato

    public void adicionarTransacao(Transacao transacao) {
    if (transacao != null) {
        transacoes.add(transacao);
        }
    }
//lista todas as transacões

    public void listarTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada");
            return;
        }
        System.out.println(" === EXTRATO === ");
        for (Transacao t : transacoes) {
            System.out.println(t);
        }
    }
//verificar se o extrato está vazio

    public boolean isVazio() {
        return transacoes.isEmpty();
    }
//Getter seguro

    public List<Transacao> getTransacoes() {
         return new ArrayList<>(transacoes);
    }
}
