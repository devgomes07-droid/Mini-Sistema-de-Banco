import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Conta conta = new Conta();

        while (true) {
            // Menu
            System.out.println("\n1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Ver Saldo");
            System.out.println("4 - Ver Histórico");
            System.out.println("5 - Sair");
            System.out.println("Escolha:");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida, digite um número.");
                sc.next();
                continue;
            }

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1: {
                    System.out.print("Valor: ");
                    if (sc.hasNextDouble()) {
                        double valor = sc.nextDouble();
                        if (valor > 0) {
                            conta.depositar(valor);
                            System.out.println("Depósito realizado!");
                        } else {
                            System.out.println("Valor inválido.");
                        }
                    } else {
                        System.out.println("Valor inválido.");
                        sc.next();
                    }
                    break;
                }
                case 2: {
                    System.out.print("Valor: ");
                    if (sc.hasNextDouble()) {
                        double valor = sc.nextDouble();
                        if (conta.sacar(valor)) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Saque não realizado, saldo insuficiente.");
                        }
                    } else {
                        System.out.println("Valor inválido.");
                        sc.next();
                    }
                    break;
                }
                case 3:
                    System.out.println("Saldo: " + conta.getSaldo());
                    break;
                case 4:
                    System.out.println("Histórico:");
                    System.out.println(conta.getHistorico());
                    break;
                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}