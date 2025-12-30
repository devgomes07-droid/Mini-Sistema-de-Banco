import java.util.ArrayList;

public class Conta {
    private double saldo;
    private ArrayList <String> historico;

    public Conta() {
        saldo = 0;
        historico = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo = saldo + valor;
            historico.add("Depósito: " + valor);
        }
    }
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        historico.add("Saque: " + valor);
        return true;
        }
         return false;
    }
    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return new ArrayList<>(historico);
    }
}

