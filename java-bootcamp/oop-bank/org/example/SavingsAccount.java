package org.example;

public class SavingsAccount extends Account {
  public SavingsAccount(final Client client) {
    super(client);
  }

  public SavingsAccount(final Client client, final double balance) {
    super(client, balance);
  }
}
