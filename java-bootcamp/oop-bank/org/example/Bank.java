package org.example;

import java.util.List;

public class Bank {
  private final String name;
  private List<IAccount> accounts;

  public Bank(final String name) {
    this.name = name;
    this.accounts = List.of();
  }

  public void addAccount(IAccount account) {
    this.accounts.add(account);
  }

  public void addAccounts(List<IAccount> accounts) {
    this.accounts.addAll(accounts);
  }

  @Override
  public String toString() {
    return "Bank [name=" + name + ", accounts=" + accounts + "]";
  }
}
