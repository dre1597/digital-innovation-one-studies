package org.example;

public abstract class Account implements IAccount {
  protected static final int DEFAULT_AGENCY = 1;
  private static int sequential = 1;
  protected int agency;
  protected int accountNumber;
  protected double balance;
  protected Client client;

  protected Account(final Client client, final int agency, final int accountNumber, final double balance) {
    if (agency < 0 || accountNumber < 0 || balance < 0) {
      throw new IllegalArgumentException("The agency, account number and balance must be greater than zero");
    }

    if (client == null) {
      throw new IllegalArgumentException("The client cannot be null");
    }
    
    this.client = client;
    this.agency = agency == 0 ? DEFAULT_AGENCY : agency;
    this.accountNumber = accountNumber == 0 ? sequential++ : accountNumber;
    this.balance = balance;
  }

  protected Account(final Client client) {
    this(client, 0, 0, 0);
  }

  protected Account(final Client client, final double balance) {
    this(client, 0, 0, balance);

  }

  public void deposit(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("The amount must be greater than zero");
    }

    balance += amount;
  }

  public void withdraw(double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("The amount must be greater than zero");
    }

    if (amount > balance) {
      throw new IllegalArgumentException("The amount must not be greater than the balance");
    }

    balance -= amount;
  }

  public void transfer(IAccount destination, double amount) {
    withdraw(amount);
    destination.deposit(amount);
  }

  @Override
  public String toString() {
    return "Agency: " + agency + ", account number: " + accountNumber + ", balance: " + balance + ", client: " + client;
  }
}
