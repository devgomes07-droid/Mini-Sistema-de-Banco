import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Conta> contas = new ArrayList<>();
    static Conta contaAtual = null;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== BANCO =====");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Selecionar conta");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Ver saldo");
            System.out.println("6 - Transferir");
            System.out.println("7 - Ver histórico");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> selecionarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> verSaldo();
                case 6 -> transferir();
                case 7 -> verHistorico();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 0);
    }
    // ===== FUNÇÕES =====
    static void criarConta() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = sc.nextLine();
        if (!nome.matches("[a-zA-Z ]+") || !sobrenome.matches("[a-zA-Z ]+")) {
            System.out.println("Nome inválido.");
            return;
        }
        Conta nova = new Conta(nome, sobrenome);
        contas.add(nova);
        contaAtual = nova;

        System.out.println("Conta criada e selecionada.");
    }
    static void selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + " - " + contas.get(i).getNomeCompleto());
        }
        System.out.print("Escolha: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 0 || index >= contas.size()) {
            System.out.println("Conta inválida.");
            return;
        }
        contaAtual = contas.get(index);
        System.out.println("Conta selecionada.");
    }
    static void depositar() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return;
        }
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        if (contaAtual.depositar(valor))
            System.out.println("Depósito realizado.");
        else
            System.out.println("Erro no depósito.");
    }
    static void sacar() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return;
        }
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        if (contaAtual.sacar(valor))
            System.out.println("Saque realizado.");
        else
            System.out.println("Erro no saque.");
    }
    static void verSaldo() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return;
        }
        System.out.println("Saldo: R$" + contaAtual.getSaldo());
    }
    static void transferir() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return;
        }
        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + " - " + contas.get(i).getNomeCompleto());
        }
        System.out.print("Destino: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 0 || index >= contas.size() || contas.get(index) == contaAtual) {
            System.out.println("Conta inválida.");
            return;
        }
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        if (contaAtual.transferir(contas.get(index), valor))
            System.out.println("Transferência realizada.");
        else
            System.out.println("Erro na transferência.");
    }
    static void verHistorico() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return;
        }
        contaAtual.mostrarHistorico();
    }
}