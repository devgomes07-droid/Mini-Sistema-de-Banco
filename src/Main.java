import model.Conta;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Conta> contas = new ArrayList<>();
    private static Conta contaAtual = null;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> criarConta();
                case 2 -> selecionarConta();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> verSaldo();
                case 6 -> transferir();
                case 7 -> verHistorico();
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ================= MENU =================

    private static void exibirMenu() {
        System.out.println("\n===== BANCO =====");
        System.out.println("Conta selecionada: " +
                (contaAtual == null ? "Nenhuma" : contaAtual.getNomeCompleto()));
        System.out.println("1 - Criar conta");
        System.out.println("2 - Selecionar conta");
        System.out.println("3 - Depositar");
        System.out.println("4 - Sacar");
        System.out.println("5 - Ver saldo");
        System.out.println("6 - Transferir");
        System.out.println("7 - Ver histórico");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }
    private static int lerOpcao() {
        while (!sc.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            sc.next();
        }
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    // ================= OPERAÇÕES =================

    private static void criarConta() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = sc.nextLine();

        if (!nome.matches("[a-zA-Z ]+") || !sobrenome.matches("[a-zA-Z ]+")) {
            System.out.println("Nome ou sobrenome inválido.");
            return;
        }

        Conta novaConta = new Conta(nome, sobrenome);
        contas.add(novaConta);
        contaAtual = novaConta;

        System.out.println("Conta criada e selecionada.");
    }
    private static void selecionarConta() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        listarContas();
        System.out.print("Escolha o índice: ");
        int index = lerOpcao();

        if (index < 0 || index >= contas.size()) {
            System.out.println("Conta inválida.");
            return;
        }
        contaAtual = contas.get(index);
        System.out.println("Conta selecionada.");
    }
    private static void depositar() {
        if (!verificarContaSelecionada()) return;

        System.out.print("Valor do depósito: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        if (contaAtual.depositar(valor)) {
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Erro ao realizar depósito.");
        }
    }
    private static void sacar() {
        if (!verificarContaSelecionada()) return;

        System.out.print("Valor do saque: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        if (contaAtual.sacar(valor)) {
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Erro ao realizar saque.");
        }
    }
    private static void verSaldo() {
        if (!verificarContaSelecionada()) return;

        System.out.println("Saldo atual: R$ " + contaAtual.getSaldo());
    }
    private static void transferir() {
        if (!verificarContaSelecionada()) return;

        listarContas();
        System.out.print("Conta destino: ");
        int index = lerOpcao();

        if (index < 0 || index >= contas.size() || contas.get(index) == contaAtual) {
            System.out.println("Conta destino inválida.");
            return;
        }
        System.out.print("Valor da transferência: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        if (contaAtual.transferir(contas.get(index), valor)) {
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Erro na transferência.");
        }
    }
    private static void verHistorico() {
        if (!verificarContaSelecionada()) return;

        System.out.println("Histórico da conta:");
        contaAtual.exibirHistorico(); // método que você vai ter na Conta
    }

    // ================= HELPERS ================

    private static boolean verificarContaSelecionada() {
        if (contaAtual == null) {
            System.out.println("Nenhuma conta selecionada.");
            return false;
        }
        return true;
    }
    private static void listarContas() {
        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + " - " + contas.get(i).getNomeCompleto());
        }
    }
}
