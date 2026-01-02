import java.util.ArrayList;

public class Conta {
    private String nome;
    private String sobrenome;
    private double saldo;
    private ArrayList<String> historico;

    public Conta(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.saldo = 0.0;
        this.historico = new ArrayList<>();
        historico.add("Conta criada");
    }
    public boolean depositar(double valor) {
        if (valor <= 0) return false;
        saldo += valor;
        historico.add("Depósito: R$ " + valor);
        return true;
    }
    public boolean sacar(double valor) {
        if (valor <= 0 || valor > saldo) return false;

        saldo -= valor;
        historico.add("Saque: R$ " + valor);
        return true;
    }
    public boolean transferir(Conta destino, double valor) {
        if (destino == null) return false;
        if (valor <= 0 || valor > saldo) return false;

        saldo -= valor;
        destino.saldo += valor;

        historico.add("Transferência enviada: R$ " + valor + " para " + destino.getNomeCompleto());
        destino.historico.add("Transferência recebida: R$ " + valor + " de " + getNomeCompleto());

        return true;
    }
    public void mostrarHistorico() {
        for (String h : historico) {
            System.out.println(h);
        }
    }
    public double getSaldo() {
        return saldo;
    }
    public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
}