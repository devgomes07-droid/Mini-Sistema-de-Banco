import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Conta conta = new Conta();

        while (true) {
            System.out.println("\n1 - Depositar");
            System.out.println("2 - Sacar");
            System.out.println("3 - Ver Saldo");
            System.out.println("4 - Ver Histórico");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            if (!sc.hasNextInt()) {
                System.out.println("Entrada inválida.");
                sc.next();
                continue;
            }

            int opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Valor do depósito: ");
                    if (sc.hasNextDouble()) {
                        double valor = sc.nextDouble();
                        if (conta.depositar(valor)) {
                            System.out.println("Depósito realizado com sucesso!");
                        } else {
                            System.out.println("Valor inválido para depósito.");
                        }
                    } else {
                        System.out.println("Entrada inválida.");
                        sc.next();
                    }
                    break;

                case 2:
                    System.out.print("Valor do saque: ");
                    if (sc.hasNextDouble()) {
                        double valor = sc.nextDouble();
                        if (conta.sacar(valor)) {
                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Saque não realizado. Verifique o saldo.");
                        }
                    } else {
                        System.out.println("Entrada inválida.");
                        sc.next();
                    }
                    break;

                case 3:
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;

                case 4:
                    System.out.println(" === EXTRATO === ");
                    for (String registro : conta.getHistorico()) {
                        System.out.println(registro);
                }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

