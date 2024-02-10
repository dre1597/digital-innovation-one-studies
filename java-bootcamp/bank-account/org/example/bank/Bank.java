package org.example.bank;

public class Bank {
  public static void main(String[] args) {
    try (var scanner = new java.util.Scanner(System.in)) {
      System.out.print("Por favor, digite o número da sua conta: ");
      int accountNumber = scanner.nextInt();

      System.out.print("Por favor, digite o número da sua agência: ");
      String agency = scanner.next();

      System.out.print("Por favor, digite o seu nome: ");
      String clientName = scanner.next();

      System.out.print("Por favor, digite o seu saldo: ");
      double balance = scanner.nextDouble();

      System.out.printf("Olá, %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %s, e seu saldo R$%.2f já está disponível para saque%n", clientName, agency, accountNumber, balance);

    }
  }
}
