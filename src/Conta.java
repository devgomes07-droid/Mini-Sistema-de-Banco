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
        if (valor <= 0 || valor > saldo) {
            return false;
        }
        saldo -= valor;
        historico.add("[Saque] Valor: " + valor);
        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return new ArrayList<>(historico);
    }
}
