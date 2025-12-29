public class Conta {
    private int numero;
    private String titular;
    private double saldo;
    private String historico = "";

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            historico += "Depósito: " + valor + "\n";
        }
    }
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            historico += "Saque: -" + valor + "\n";
            return true;
        }
         return false;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getHistorico() {
        return historico;
    }
}

