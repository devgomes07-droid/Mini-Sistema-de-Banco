import java.util.ArrayList;

public class Conta {

    private double saldo;
    private ArrayList<String> historico;

    public Conta() {
        saldo = 0;
        historico = new ArrayList<>();
    }

    public boolean depositar(double valor) {
        if (valor <= 0) {
            return false;
        }
        saldo += valor;
        historico.add("[DEPÓSITO] Valor: " + valor);
        return true;
    }

    public boolean sacar(double valor) {
        if (valor <= 0 || valor > saldo) {  // corrigido o || para lógica correta
            return false;
        }
        saldo -= valor;
        historico.add("[SAQUE] Valor: " + valor);
        return true;
    }

    public boolean transferir(Conta destino, double valor) {  // corrigido o nome do método
        if (valor <= 0 || valor > saldo) {
            return false;
        }
        saldo -= valor;
        destino.depositar(valor);
        historico.add("[TRANSFERÊNCIA ENVIADA] Valor: " + valor);
        destino.adicionarHistorico("[TRANSFERÊNCIA RECEBIDA] Valor: " + valor);
        return true;
    }

    // método auxiliar para adicionar histórico de forma segura

    private void adicionarHistorico(String texto) {
        historico.add(texto);
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return new ArrayList<>(historico);
    }
}