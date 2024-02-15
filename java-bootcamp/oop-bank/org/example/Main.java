package org.example;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    final var clientOne = new Client("John");
    final var accountOne = new CheckingAccount(clientOne);
    System.out.println(accountOne);

    final var clientTwo = new Client("Jane");
    final var accountTwo = new SavingsAccount(clientTwo);
    System.out.println(accountTwo);

    final var bank = new Bank("Bank of Brazil");
    bank.addAccounts(List.of(accountOne, accountTwo));
    System.out.println(bank);

    final var accountThree = new CheckingAccount(clientOne, 1000);
    System.out.println(accountThree);

    final var accountFour = new SavingsAccount(clientTwo, 1000);
    System.out.println(accountFour);

    bank.addAccount(accountThree);
    bank.addAccount(accountFour);
    System.out.println(bank);

    accountOne.deposit(100);
    accountTwo.deposit(100);
    accountThree.deposit(100);
    accountFour.deposit(100);

    accountFour.transfer(accountOne, 50);
  }
}
