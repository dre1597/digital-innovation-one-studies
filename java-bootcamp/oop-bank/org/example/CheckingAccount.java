package org.example;

public class CheckingAccount extends Account {

  public CheckingAccount(final Client client) {
    super(client);
  }

  public CheckingAccount(final Client client, final double balance) {
    super(client, balance);
  }
}
